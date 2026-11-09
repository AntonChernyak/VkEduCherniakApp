package com.example.vkeducherniakapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppListItemDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("iconUrl")
    val imageUrl: String,
    @SerialName("category")
    val categoryKey: String
)
