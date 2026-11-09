package com.example.vkeducherniakapp.di

import com.example.vkeducherniakapp.data.mapper.AppListItemMapper
import com.example.vkeducherniakapp.data.repository.AppListRepository
import com.example.vkeducherniakapp.data.retrofit.AppApi
import com.example.vkeducherniakapp.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppListRepository(appApi: AppApi, mapper: AppListItemMapper): AppRepository {
        return AppListRepository(
            api = appApi,
            mapper = mapper
        )
    }
}