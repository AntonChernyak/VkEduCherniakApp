package com.example.vkeducherniakapp.di

import com.example.vkeducherniakapp.data.retrofit.AppApi
import com.example.vkeducherniakapp.data.util.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): AppApi {
        return retrofit.create(AppApi::class.java)
    }



//    @Provides
//    @Singleton
//    fun provideAppDetailsRepository(appApi: AppApi): AppDetailsRepository {
//        return AppDetailsRepositoryImpl(appApi)
//    }

/*    @Provides
    @Singleton
    fun provideGetAppDetailsUseCase(repository: AppDetailsRepository): GetAppDetailsUseCase {
        return GetAppDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideAppDetailsDao(database: AppDatabase): AppDetailsDao {
        return database.appDetailsDao()
    }

    @Provides
    @Singleton
    fun provideAppDetailsEntityMapper(): AppDetailsEntityMapper {
        return AppDetailsEntityMapper()
    }

    @Provides
    @Singleton
    fun provideAppDetailsMapper(): AppDetailsMapper {
        return AppDetailsMapper()
    }

    @Provides
    @Singleton
    fun provideAppDetailsRepository(
        api: AppApi,
        dao: AppDetailsDao,
        mapper: AppDetailsMapper,
        entityMapper: AppDetailsEntityMapper
    ): AppDetailsRepository {
        return AppDetailsRepositoryImpl(api, dao, mapper, entityMapper)
    }

    @Provides
    @Singleton
    fun provideSimilarAppsApi(): SimilarAppsApi = SimilarAppsApi()

    @Provides
    @Singleton
    fun provideSimilarAppMapper(): SimilarAppMapper = SimilarAppMapper()

    @Provides
    @Singleton
    fun provideSimilarAppsRepository(
        api: SimilarAppsApi,
        mapper: SimilarAppMapper,
    ): SimilarAppsRepository = SimilarAppsRepositoryImpl(api, mapper)

    @Provides
    @Singleton
    fun provideGetSimilarAppsUseCase(repository: SimilarAppsRepository): GetSimilarAppsUseCase =
        GetSimilarAppsUseCase(repository)*/
}
