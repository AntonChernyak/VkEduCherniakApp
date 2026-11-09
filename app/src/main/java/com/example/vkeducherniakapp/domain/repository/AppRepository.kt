package com.example.vkeducherniakapp.domain.repository

import com.example.vkeducherniakapp.domain.model.AppListItem


interface AppRepository {
    suspend fun getApps(): List<AppListItem>
}