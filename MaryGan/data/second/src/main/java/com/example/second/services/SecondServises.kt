package com.example.second.services
import com.example.second.models.ServerResponse
import retrofit2.Response
import retrofit2.http.GET

interface SecondServises {
    @GET("testAndroidData")
    suspend fun getRaitings(): Response<ServerResponse>
}