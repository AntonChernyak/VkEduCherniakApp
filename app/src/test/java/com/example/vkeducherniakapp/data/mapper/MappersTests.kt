package com.example.vkeducherniakapp.data.mapper

import com.example.vkeducherniakapp.data.dto.AppDetailsDto
import com.example.vkeducherniakapp.data.dto.AppListItemDto
import com.example.vkeducherniakapp.data.dto.SimilarAppDto
import com.example.vkeducherniakapp.domain.model.AppCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MappersTest {

    private val appListItemMapper = AppListItemMapper()
    private val appDetailsMapper = AppDetailsMapper()
    private val similarAppMapper = SimilarAppMapper()

    @Test
    fun `AppListItemMapper maps all fields correctly`() {
        val dto = AppListItemDto(
            id = "app_1",
            title = "Telegram",
            description = "Fast messaging app",
            imageUrl = "https://example.com/tg.png",
            categoryKey = "COMMUNICATION"
        )

        val result = appListItemMapper.toDomain(dto)

        assertEquals("app_1", result.id)
        assertEquals("Telegram", result.title)
        assertEquals("Fast messaging app", result.description)
        assertEquals("https://example.com/tg.png", result.imageUrl)
        assertEquals(AppCategory.findCategory("COMMUNICATION"), result.category)
        assertNull(result.rating)
    }

    @Test
    fun `AppListItemMapper handles unknown categoryKey and empty strings`() {
        val dto = AppListItemDto(
            id = "",
            title = "",
            description = "",
            imageUrl = "",
            categoryKey = "UNKNOWN_KEY"
        )

        val result = appListItemMapper.toDomain(dto)

        assertEquals("", result.id)
        assertEquals("", result.title)
        assertEquals(AppCategory.findCategory("UNKNOWN_KEY"), result.category)
    }

    @Test
    fun `AppDetailsMapper maps all fields and converts size to Float`() {
        val dto = AppDetailsDto(
            id = "app_10",
            name = "Navigator",
            developer = "Yandex",
            category = AppCategory.NAVIGATION,
            ageRating = 6,
            size = 120.0,
            icon = "https://example.com/icon.png",
            screenshots = listOf("url1", "url2"),
            description = "Offline maps and navigation"
        )

        val result = appDetailsMapper.toDomain(dto)

        assertEquals("app_10", result.id)
        assertEquals("Navigator", result.name)
        assertEquals("Yandex", result.developer)
        assertEquals(AppCategory.NAVIGATION, result.category)
        assertEquals(6, result.ageRating)
        assertEquals(120f, result.size, 0.001f)
        assertEquals("https://example.com/icon.png", result.iconUrl)
        assertEquals(listOf("url1", "url2"), result.screenshotUrlList)
        assertEquals("Offline maps and navigation", result.description)
    }

    @Test
    fun `AppDetailsMapper handles empty screenshots and zero size`() {
        val dto = AppDetailsDto(
            id = "app_11",
            name = "Minimal App",
            developer = "Dev",
            category = AppCategory.TOOLS,
            ageRating = 0,
            size = 0.0,
            icon = "",
            screenshots = emptyList(),
            description = ""
        )

        val result = appDetailsMapper.toDomain(dto)

        assertTrue(result.screenshotUrlList.isEmpty())
        assertEquals(0f, result.size, 0.001f)
    }

    @Test
    fun `SimilarAppMapper maps all fields correctly`() {
        val dto = SimilarAppDto(
            id = "sim_1",
            name = "VK Music",
            iconUrl = "https://example.com/music.png"
        )

        val result = similarAppMapper.toDomain(dto)

        assertEquals("sim_1", result.id)
        assertEquals("VK Music", result.name)
        assertEquals("https://example.com/music.png", result.iconUrl)
    }

    @Test
    fun `SimilarAppMapper handles empty fields`() {
        val dto = SimilarAppDto(
            id = "",
            name = "",
            iconUrl = ""
        )

        val result = similarAppMapper.toDomain(dto)

        assertEquals("", result.id)
        assertEquals("", result.name)
        assertEquals("", result.iconUrl)
    }
}