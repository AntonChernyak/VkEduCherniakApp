package com.example.vkeducherniakapp.domain.model

data class AppListItem(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val category: AppCategory
)
