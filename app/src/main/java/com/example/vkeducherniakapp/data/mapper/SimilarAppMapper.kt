package com.example.vkeducherniakapp.data.mapper

import com.example.vkeducherniakapp.data.dto.SimilarAppDto
import com.example.vkeducherniakapp.domain.model.SimilarApp


class SimilarAppMapper {
    fun toDomain(dto: SimilarAppDto): SimilarApp = SimilarApp(
        id = dto.id,
        name = dto.name,
        iconUrl = dto.iconUrl,
    )
}