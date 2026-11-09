package com.example.vkeducherniakapp.domain.repository

import com.example.vkeducherniakapp.domain.model.SimilarApp

interface SimilarAppsRepository {
    suspend fun getSimilarApps(): List<SimilarApp>
}
