package com.vladislav.posts.testapplication.di.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.vladislav.posts.testapplication.data.network.api.Api
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @ExperimentalSerializationApi
    @Provides
    fun provideJson(): Json =
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
        }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideUnauthorizedRetrofit(json: Json): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)

    private companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}