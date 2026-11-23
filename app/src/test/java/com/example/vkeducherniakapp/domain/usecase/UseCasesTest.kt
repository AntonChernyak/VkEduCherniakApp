package com.example.vkeducherniakapp.domain.usecase

import com.example.vkeducherniakapp.domain.model.AppDetails
import com.example.vkeducherniakapp.domain.model.SimilarApp
import com.example.vkeducherniakapp.domain.repository.AppDetailsRepository
import com.example.vkeducherniakapp.domain.repository.SimilarAppsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class UseCasesTest {

    @Test
    fun `GetAppDetailsUseCase invokes repository with given id and emits details`() = runTest {
        val repository = mockk<AppDetailsRepository>()
        val expectedDetails = mockk<AppDetails>()
        coEvery { repository.getAppDetails("app_123") } returns flowOf(expectedDetails)

        val useCase = GetAppDetailsUseCase(repository)
        val result = useCase("app_123").first()

        assertEquals(expectedDetails, result)
        coVerify(exactly = 1) { repository.getAppDetails("app_123") }
    }

    @Test
    fun `GetAppDetailsUseCase passes empty id to repository`() = runTest {
        val repository = mockk<AppDetailsRepository>()
        val expectedDetails = mockk<AppDetails>()
        coEvery { repository.getAppDetails("") } returns flowOf(expectedDetails)

        val useCase = GetAppDetailsUseCase(repository)
        val result = useCase("").first()

        assertEquals(expectedDetails, result)
        coVerify(exactly = 1) { repository.getAppDetails("") }
    }

    @Test(expected = IOException::class)
    fun `GetAppDetailsUseCase propagates exception from repository`() = runTest {
        val repository = mockk<AppDetailsRepository>()
        coEvery { repository.getAppDetails("app_err") } throws IOException("Failed to load details")

        val useCase = GetAppDetailsUseCase(repository)
        useCase("app_err")
    }

    @Test
    fun `GetSimilarAppsUseCase returns list of similar apps from repository`() = runTest {
        val repository = mockk<SimilarAppsRepository>()
        val similarApps = listOf(mockk<SimilarApp>(), mockk<SimilarApp>())
        coEvery { repository.getSimilarApps() } returns similarApps

        val useCase = GetSimilarAppsUseCase(repository)
        val result = useCase()

        assertEquals(similarApps, result)
        assertEquals(2, result.size)
        coVerify(exactly = 1) { repository.getSimilarApps() }
    }

    @Test
    fun `GetSimilarAppsUseCase returns empty list when repository returns empty`() = runTest {
        val repository = mockk<SimilarAppsRepository>()
        coEvery { repository.getSimilarApps() } returns emptyList()

        val useCase = GetSimilarAppsUseCase(repository)
        val result = useCase()

        assertTrue(result.isEmpty())
        coVerify(exactly = 1) { repository.getSimilarApps() }
    }

    @Test(expected = RuntimeException::class)
    fun `GetSimilarAppsUseCase propagates exception from repository`() = runTest {
        val repository = mockk<SimilarAppsRepository>()
        coEvery { repository.getSimilarApps() } throws RuntimeException("Repository error")

        val useCase = GetSimilarAppsUseCase(repository)
        useCase()
    }
}