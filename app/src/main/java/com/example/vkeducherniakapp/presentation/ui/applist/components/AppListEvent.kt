package com.example.vkeducherniakapp.presentation.ui.applist.components

sealed interface AppListEvent {
    data object ShowToolbarMessage : AppListEvent
}