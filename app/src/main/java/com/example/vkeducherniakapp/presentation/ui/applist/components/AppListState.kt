package com.example.vkeducherniakapp.presentation.ui.applist.components

import androidx.compose.runtime.Immutable
import com.example.vkeducherniakapp.domain.model.AppListItem

@Immutable
sealed interface AppListState {
    data object Loading : AppListState
    data object Error : AppListState
    data class Content(
        val apps: List<AppListItem>
    ) : AppListState
}