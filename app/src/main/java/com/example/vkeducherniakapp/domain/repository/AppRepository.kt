package com.example.vkeducherniakapp.domain.repository

import com.example.vkeducherniakapp.domain.model.AppListItem

interface AppRepository {
    fun getApps(): List<AppListItem>
    fun addApp(app: AppListItem)
    fun deleteApp(appId: Int)
    fun findAppById(id: Int): AppListItem
}