package com.example.vkeducherniakapp.presentation.ui.appdetails

import androidx.compose.runtime.Immutable
import com.example.vkeducherniakapp.domain.model.AppDetails
import com.example.vkeducherniakapp.domain.model.SimilarApp

@Immutable
sealed interface AppDetailsState {
    data object Error : AppDetailsState
    data object Loading : AppDetailsState
    data class Content(
        val appDetails: AppDetails,
        val similarApps: List<SimilarApp>,
        val descriptionCollapsed: Boolean,
    ) : AppDetailsState
}