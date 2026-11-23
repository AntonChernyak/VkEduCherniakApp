package com.example.vkeducherniakapp.domain.repository

import com.example.vkeducherniakapp.domain.model.AppDetails
import kotlinx.coroutines.flow.Flow

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): Flow<AppDetails>
}