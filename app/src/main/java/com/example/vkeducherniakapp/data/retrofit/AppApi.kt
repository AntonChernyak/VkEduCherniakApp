package com.example.vkeducherniakapp.data.retrofit

import com.example.vkeducherniakapp.data.dto.AppDetailsDto
import com.example.vkeducherniakapp.data.dto.AppListItemDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {

    @GET("catalog")
    suspend fun getCatalog(): List<AppListItemDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto

}