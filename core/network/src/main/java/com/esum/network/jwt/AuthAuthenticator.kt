package com.esum.network.jwt

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: JwtTokenManager,
    private val refreshTokenService: RefreshTokenService
) : Authenticator {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking {
            tokenManager.getAccessToken()
        }
        synchronized(this) {
            val updateToken = runBlocking {
                tokenManager.getAccessToken()
            }
            val token = if (currentToken != updateToken) updateToken else {
                val newSessionResponse = runBlocking { refreshTokenService.refresh() }
                if (newSessionResponse.isSuccessful && newSessionResponse.body() != null) {
                    newSessionResponse.body()?.let {
                        runBlocking {
                            tokenManager.saveAccessToken(it.data!!.token)
                            tokenManager.saveRefreshToken(it.data.token)
                        }
                        it.data!!.token
                    }
                } else null
            }
            return if (token != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token").build() else null
        }

    }
}