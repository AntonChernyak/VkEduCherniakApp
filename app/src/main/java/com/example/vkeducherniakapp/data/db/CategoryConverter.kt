package com.example.vkeducherniakapp.data.db

import androidx.room.TypeConverter
import com.example.vkeducherniakapp.domain.model.AppCategory

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: AppCategory): String = category.name

    @TypeConverter
    fun toCategory(categoryName: String): AppCategory = AppCategory.valueOf(categoryName)
}