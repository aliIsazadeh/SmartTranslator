package com.esum.network.jwt

import com.esum.testosm.domain.model.AuthResponse
import com.esum.testosm.domain.model.Data
import retrofit2.Response

interface RefreshTokenService {
    fun refresh(): Response<AuthResponse> {

        return Response.success(
            AuthResponse(
                Data(
                    "",
                    "",
                    1,
                    1,
                    1,
                    "",
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJ1c2VycyIsImlzcyI6ImVzb21lLmNvbSIsImVtYWlsIjoibWFtYWRpQG1vcmFkaS5jb20ifQ.Rvmn8LdR6TKpFJG8Sc2xksu8IfscnyhypV9hBGgJm3Y"
                ), ""
            )
        )
    }
}