package com.esum.network.jwt

interface JwtTokenManager {

    suspend fun saveAccessToken(token : String)
    suspend fun saveRefreshToken(token: String)
    suspend fun getAccessToken() : String?
    suspend fun getRefreshToken() : String?
    suspend fun clearAllTokens()

}