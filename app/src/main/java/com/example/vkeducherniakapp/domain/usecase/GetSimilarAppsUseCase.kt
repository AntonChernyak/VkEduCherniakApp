package com.example.vkeducherniakapp.domain.usecase

import com.example.vkeducherniakapp.domain.model.SimilarApp

class GetSimilarAppsUseCase(
    private val repository: SimilarAppsRepository,
) {
    suspend operator fun invoke(): List<SimilarApp> = repository.getSimilarApps()
}
