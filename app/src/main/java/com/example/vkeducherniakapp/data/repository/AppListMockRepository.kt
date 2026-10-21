package com.example.vkeducherniakapp.data.repository

import com.example.vkeducherniakapp.domain.model.AppCategory
import com.example.vkeducherniakapp.domain.model.AppListItem
import com.example.vkeducherniakapp.domain.repository.AppRepository

class AppListMockRepository : AppRepository {

    private val _apps = mutableListOf(

        AppListItem(
            id = 1,
            title = "Telegram",
            category = AppCategory.SOCIAL,
            imageUrl = "https://hydropower.ru/images/tg.jpg",
            description = "Быстрый и защищенный мессенджер"
        ),
        AppListItem(
            id = 2,
            title = "Яндекс Музыка",
            category = AppCategory.MUSIC,
            imageUrl = "https://alpha-ag.org/uploads/posts/2021-12/jandeksmuzyka-podkasty-knigi.webp",
            description = "Треки, подкасты и плейлисты"
        ),
        AppListItem(
            id = 3,
            title = "VK",
            category = AppCategory.SOCIAL,
            imageUrl = "https://images.icon-icons.com/2699/PNG/512/vk_logo_icon_168247.png",
            description = "Социальная сеть для общения и развлечений"
        ),
        AppListItem(
            id = 4,
            title = "T-Bank (Т-Банк)",
            category = AppCategory.FINANCE,
            imageUrl = "https://modulmoney.ru/wp-content/uploads/2025/09/525366d7a43a3460612dcf6b5ae8db3223d5f501.jpg",
            description = "Мобильный банк и финансовые сервисы"
        ),
        AppListItem(
            id = 5,
            title = "Wildberries",
            category = AppCategory.SHOPPING,
            imageUrl = "https://images.meme-arsenal.com/eb03cf0bf5a624231a3fcebaab6d175a.jpg",
            description = "Интернет-магазин одежды, электроники и товаров"
        ),
        AppListItem(
            id = 6,
            title = "Кинопоиск",
            category = AppCategory.ENTERTAINMENT,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Kinopoisk_colored_square_icon.svg/1280px-Kinopoisk_colored_square_icon.svg.png",
            description = "Онлайн-кинотеатр с фильмами и сериалами"
        ),
        AppListItem(
            id = 7,
            title = "2GIS",
            category = AppCategory.NAVIGATION,
            imageUrl = "https://static.tildacdn.com/tild3061-6565-4135-b938-333938626435/2gis.webp",
            description = "Подробная карта города и справочник организаций"
        )
    )

    override fun getApps(): List<AppListItem> = _apps.toList()

    override fun addApp(app: AppListItem) {
        _apps.add(app)
    }

    override fun deleteApp(appId: Int) {
        _apps.removeIf { app -> app.id == appId }
    }

    override fun findAppById(id: Int): AppListItem {
        return _apps.find { app -> app.id == id }
            ?: throw Exception("Приложение не найдено по id = $id")
    }
}