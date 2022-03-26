package com.omsuperg.msranking.service

import com.omsuperg.msranking.model.ServerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character/{server}/{character}")
    suspend fun getCharacterInfo(
        @Path("server") server: String,
        @Path("character") character: String
    ): Response<ServerResponse>
}