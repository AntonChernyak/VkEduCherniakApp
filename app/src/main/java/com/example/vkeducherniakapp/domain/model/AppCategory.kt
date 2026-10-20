package com.example.vkeducherniakapp.domain.model

enum class AppCategory(val title: String) {
    GAMES("Игры"),
    FINANCE("Финансы"),
    SHOPPING("Покупки"),
    SOCIAL("Общение"),
    ENTERTAINMENT("Развлечения"),
    MUSIC("Музыка и аудио"),
    NAVIGATION("Навигация и транспорт"),
    TOOLS("Инструменты"),
    OTHER("Другое");

    companion object {
        fun findCategory(backendKey: String?): AppCategory {
            return AppCategory.entries.find { it.title.equals(backendKey, ignoreCase = true) }
                ?: OTHER
        }
    }
}