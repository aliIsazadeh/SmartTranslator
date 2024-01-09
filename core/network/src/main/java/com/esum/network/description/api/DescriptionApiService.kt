package com.esum.network.description.api

import com.esum.network.description.model.DescriptionResult
import com.esum.network.description.model.DescriptionResultItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DescriptionApiService {

    @GET("entries/{language}/{word}")
    suspend fun getDescription(
        @Path("language") language : String ,
        @Path("word") word : String ,
    ) : Response<DescriptionResult>

}