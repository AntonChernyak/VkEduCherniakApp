package com.example.vkeducherniakapp.data.repository

import com.example.vkeducherniakapp.data.mapper.SimilarAppMapper
import com.example.vkeducherniakapp.domain.model.SimilarApp
import com.example.vkeducherniakapp.domain.repository.SimilarAppsRepository
import javax.inject.Inject

class SimilarAppsRepositoryImpl @Inject constructor(
    private val api: SimilarAppsApi,
    private val mapper: SimilarAppMapper,
) : SimilarAppsRepository {
    override suspend fun getSimilarApps(): List<SimilarApp> =
        api.getSimilarApps().map(mapper::toDomain)
}
