package com.example.vkeducherniakapp.presentation.ui.applist.components

sealed interface AppListErrorState {
    data object Network : AppListErrorState
    data object Server : AppListErrorState
    data class Unknown(val message: String?) : AppListErrorState
}