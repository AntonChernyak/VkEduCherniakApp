package com.example.vkeducherniakapp.data.repository

import android.util.Log
import com.example.vkeducherniakapp.data.db.AppDetailsDao
import com.example.vkeducherniakapp.data.db.entity.AppDetailsEntity
import com.example.vkeducherniakapp.data.db.entity.AppDetailsEntityMapper
import com.example.vkeducherniakapp.data.dto.AppDetailsDto
import com.example.vkeducherniakapp.data.dto.AppListItemDto
import com.example.vkeducherniakapp.data.dto.SimilarAppDto
import com.example.vkeducherniakapp.data.mapper.AppDetailsMapper
import com.example.vkeducherniakapp.data.mapper.AppListItemMapper
import com.example.vkeducherniakapp.data.mapper.SimilarAppMapper
import com.example.vkeducherniakapp.data.retrofit.AppApi
import com.example.vkeducherniakapp.domain.model.AppDetails
import com.example.vkeducherniakapp.domain.model.AppListItem
import com.example.vkeducherniakapp.domain.model.SimilarApp
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RepositoriesTest {

    @Before
    fun setUp() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
    }

    @After
    fun tearDown() {
        unmockkStatic(Log::class)
    }

    @Test
    fun `AppListRepository getApps returns mapped domain list from api`() = runTest {
        val api = mockk<AppApi>()
        val mapper = mockk<AppListItemMapper>()
        val dto = mockk<AppListItemDto>()
        val domainItem = mockk<AppListItem>()

        coEvery { api.getCatalog() } returns listOf(dto)
        every { mapper.toDomain(dto) } returns domainItem

        val repository = AppListRepository(api, mapper)
        val result = repository.getApps()

        assertEquals(listOf(domainItem), result)
        coVerify(exactly = 1) { api.getCatalog() }
    }

    @Test(expected = IOException::class)
    fun `AppListRepository getApps throws IOException when api fails`() = runTest {
        val api = mockk<AppApi>()
        val mapper = mockk<AppListItemMapper>()

        coEvery { api.getCatalog() } throws IOException("Network error")

        val repository = AppListRepository(api, mapper)
        repository.getApps()
    }

    @Test
    fun `AppListRepository loadRatingsInParallel returns ratings map for all ids`() = runTest {
        val api = mockk<AppApi>()
        val mapper = mockk<AppListItemMapper>()

        coEvery { api.getRating("1") } returns 4.5f
        coEvery { api.getRating("2") } returns 4.9f

        val repository = AppListRepository(api, mapper)
        val result = repository.loadRatingsInParallel(listOf("1", "2"))

        assertEquals(mapOf("1" to 4.5f, "2" to 4.9f), result)
        coVerify(exactly = 1) { api.getRating("1") }
        coVerify(exactly = 1) { api.getRating("2") }
    }

    @Test
    fun `AppListRepository loadRatingsSequentially returns empty map for empty list`() = runTest {
        val api = mockk<AppApi>()
        val mapper = mockk<AppListItemMapper>()

        val repository = AppListRepository(api, mapper)
        val result = repository.loadRatingsSequentially(emptyList())

        assertEquals(emptyMap<String, Float>(), result)
        coVerify(exactly = 0) { api.getRating(any()) }
    }

    @Test
    fun `AppDetailsRepositoryImpl returns cached details without calling api`() = runTest {
        val api = mockk<AppApi>()
        val dao = mockk<AppDetailsDao>()
        val mapper = mockk<AppDetailsMapper>()
        val entityMapper = mockk<AppDetailsEntityMapper>()

        val cachedEntity = mockk<AppDetailsEntity>()
        val expectedDomain = mockk<AppDetails>()

        every { dao.getAppDetails("app_1") } returns flowOf(cachedEntity)
        every { entityMapper.toDomain(cachedEntity) } returns expectedDomain

        val repository = AppDetailsRepositoryImpl(api, dao, mapper, entityMapper)
        val result = repository.getAppDetails("app_1").first()

        assertEquals(expectedDomain, result)
        coVerify(exactly = 0) { api.getAppDetails(any()) }
        coVerify(exactly = 0) { dao.insertAppDetails(any()) }
    }

    @Test
    fun `AppDetailsRepositoryImpl fetches from api and saves to dao when cache is empty`() = runTest {
        val api = mockk<AppApi>()
        val dao = mockk<AppDetailsDao>(relaxed = true)
        val mapper = mockk<AppDetailsMapper>()
        val entityMapper = mockk<AppDetailsEntityMapper>()

        val dto = mockk<AppDetailsDto>()
        val domain = mockk<AppDetails>()
        val entityToSave = mockk<AppDetailsEntity>()

        every { dao.getAppDetails("app_2") } returns flowOf(null)
        coEvery { api.getAppDetails("app_2") } returns dto
        every { mapper.toDomain(dto) } returns domain
        every { entityMapper.toEntity(domain) } returns entityToSave

        val repository = AppDetailsRepositoryImpl(api, dao, mapper, entityMapper)
        val result = repository.getAppDetails("app_2").first()

        assertEquals(domain, result)
        coVerify(exactly = 1) { api.getAppDetails("app_2") }
        coVerify(exactly = 1) { dao.insertAppDetails(entityToSave) }
    }

    @Test
    fun `SimilarAppsRepositoryImpl getSimilarApps returns mapped domain list`() = runTest {
        val api = mockk<SimilarAppsApi>()
        val mapper = mockk<SimilarAppMapper>()
        val dto = mockk<SimilarAppDto>()
        val domain = mockk<SimilarApp>()

        coEvery { api.getSimilarApps() } returns listOf(dto)
        every { mapper.toDomain(dto) } returns domain

        val repository = SimilarAppsRepositoryImpl(api, mapper)
        val result = repository.getSimilarApps()

        assertEquals(listOf(domain), result)
        coVerify(exactly = 1) { api.getSimilarApps() }
    }
}