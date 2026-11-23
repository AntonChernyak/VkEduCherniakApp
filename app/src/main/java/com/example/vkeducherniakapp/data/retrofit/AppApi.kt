package com.example.vkeducherniakapp.data.retrofit

import com.example.vkeducherniakapp.data.dto.AppDetailsDto
import com.example.vkeducherniakapp.data.dto.AppListItemDto
import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.time.Duration.Companion.milliseconds

interface AppApi {

    @GET("catalog")
    suspend fun getCatalog(): List<AppListItemDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto

    suspend fun getRating(id: String): Float {
        delay(1500.milliseconds)
        return (30..50).random() / 10f
    }
}