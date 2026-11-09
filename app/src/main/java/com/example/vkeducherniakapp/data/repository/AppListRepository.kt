package com.example.vkeducherniakapp.data.repository

import com.example.vkeducherniakapp.data.mapper.AppListItemMapper
import com.example.vkeducherniakapp.data.retrofit.AppApi
import com.example.vkeducherniakapp.domain.model.AppListItem
import com.example.vkeducherniakapp.domain.repository.AppRepository

class AppListRepository(
    private val api: AppApi,
    private val mapper: AppListItemMapper
) : AppRepository {

     override suspend fun getApps(): List<AppListItem> {
        return api.getCatalog().map { dto ->
            mapper.toDomain(dto)
        }
    }
}