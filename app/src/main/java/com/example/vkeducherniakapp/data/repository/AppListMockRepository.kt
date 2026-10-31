package com.example.vkeducherniakapp.data.repository

import com.example.vkeducherniakapp.data.dto.AppListItemDto
import com.example.vkeducherniakapp.data.mapper.AppListItemMapper
import com.example.vkeducherniakapp.domain.model.AppListItem
import com.example.vkeducherniakapp.domain.repository.AppRepository

class AppListMockRepository(
    private val mapper: AppListItemMapper
) : AppRepository {

    private val _apps = mutableListOf(
        AppListItemDto(
            id = 1,
            title = "Telegram",
            categoryKey = "SOCIAL",
            imageUrl = "https://hydropower.ru/images/tg.jpg",
            description = "Быстрый и защищенный мессенджер"
        ),
        AppListItemDto(
            id = 2,
            title = "Яндекс Музыка",
            categoryKey = "MUSIC",
            imageUrl = "https://alpha-ag.org/uploads/posts/2021-12/jandeksmuzyka-podkasty-knigi.webp",
            description = "Треки, подкасты и плейлисты"
        ),
        AppListItemDto(
            id = 3,
            title = "VK",
            categoryKey = "SOCIAL",
            imageUrl = "https://images.icon-icons.com/2699/PNG/512/vk_logo_icon_168247.png",
            description = "Социальная сеть для общения и развлечений"
        ),
        AppListItemDto(
            id = 4,
            title = "T-Bank (Т-Банк)",
            categoryKey = "FINANCE",
            imageUrl = "https://modulmoney.ru/wp-content/uploads/2025/09/525366d7a43a3460612dcf6b5ae8db3223d5f501.jpg",
            description = "Мобильный банк и финансовые сервисы"
        ),
        AppListItemDto(
            id = 5,
            title = "Wildberries",
            categoryKey = "SHOPPING",
            imageUrl = "https://images.meme-arsenal.com/eb03cf0bf5a624231a3fcebaab6d175a.jpg",
            description = "Интернет-магазин одежды, электроники и товаров"
        ),
        AppListItemDto(
            id = 6,
            title = "Кинопоиск",
            categoryKey = "ENTERTAINMENT",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Kinopoisk_colored_square_icon.svg/1280px-Kinopoisk_colored_square_icon.svg.png",
            description = "Онлайн-кинотеатр с фильмами и сериалами"
        ),
        AppListItemDto(
            id = 7,
            title = "2GIS",
            categoryKey = "NAVIGATION",
            imageUrl = "https://static.tildacdn.com/tild3061-6565-4135-b938-333938626435/2gis.webp",
            description = "Подробная карта города и справочник организаций"
        )
    )

    override fun getApps(): List<AppListItem> {
        return _apps.map { dto ->
            mapper.toDomain(dto)
        }
    }


    override fun deleteApp(appId: Int) {
        _apps.removeIf { app -> app.id == appId }
    }

}