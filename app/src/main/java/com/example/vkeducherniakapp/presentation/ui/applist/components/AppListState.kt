package com.example.vkeducherniakapp.presentation.ui.applist.components

import androidx.compose.runtime.Immutable
import com.example.vkeducherniakapp.domain.model.AppListItem

@Immutable
sealed interface AppListState {
    data object Loading : AppListState
    data class Error(val error: AppListError) : AppListState
    data class Content(
        val apps: List<AppListItem>
    ) : AppListState
}