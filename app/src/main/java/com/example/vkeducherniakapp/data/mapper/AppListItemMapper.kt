package com.example.vkeducherniakapp.data.mapper

import com.example.vkeducherniakapp.data.dto.AppListItemDto
import com.example.vkeducherniakapp.domain.model.AppCategory
import com.example.vkeducherniakapp.domain.model.AppListItem
import javax.inject.Inject

class AppListItemMapper @Inject constructor() {

        fun toDomain(dto: AppListItemDto): AppListItem {
            return AppListItem(
                id = dto.id,
                title = dto.title,
                description = dto.description,
                imageUrl = dto.imageUrl,
                category = AppCategory.findCategory(dto.categoryKey)
            )
        }
}