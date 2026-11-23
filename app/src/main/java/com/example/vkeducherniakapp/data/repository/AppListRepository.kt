package com.example.vkeducherniakapp.data.repository

import android.util.Log
import com.example.vkeducherniakapp.data.mapper.AppListItemMapper
import com.example.vkeducherniakapp.data.retrofit.AppApi
import com.example.vkeducherniakapp.domain.model.AppListItem
import com.example.vkeducherniakapp.domain.repository.AppRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.system.measureTimeMillis

class AppListRepository(
    private val api: AppApi,
    private val mapper: AppListItemMapper
) : AppRepository {

    override suspend fun getApps(): List<AppListItem> {
        return api.getCatalog().map { dto ->
            mapper.toDomain(dto)
        }
    }

    suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> {
        val ratingsMap = mutableMapOf<String, Float>()

        val executionTime = measureTimeMillis {
            for (id in ids) {
                val rating = api.getRating(id)
                ratingsMap[id] = rating
            }
        }
        Log.d("PerformanceTest", "Sequential loading took: ${executionTime}ms for ${ids.size} apps")

        return ratingsMap
    }

    suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> = coroutineScope {
        var ratingsMap = mapOf<String, Float>()

        val executionTime = measureTimeMillis {
            val deferredRatings = ids.map { id ->
                async {
                    id to api.getRating(id)
                }
            }
            ratingsMap = deferredRatings.awaitAll().toMap()
        }
        Log.d("PerformanceTest", "Parallel loading took: ${executionTime}ms for ${ids.size} apps")

        ratingsMap
    }
}