package com.example.vkeducherniakapp.domain.usecase

import com.example.vkeducherniakapp.domain.model.AppDetails
import kotlinx.coroutines.flow.Flow

class GetAppDetailsUseCase(
    private val appDetailsRepository: AppDetailsRepository,
) {
    suspend operator fun invoke(id: String): Flow<AppDetails> =
        appDetailsRepository.getAppDetails(id)
}