package com.example.vkeducherniakapp.presentation.ui.applist.components

sealed interface AppListError {
    data object Network : AppListError
    data object Server : AppListError
    data class Unknown(val message: String?) : AppListError
}