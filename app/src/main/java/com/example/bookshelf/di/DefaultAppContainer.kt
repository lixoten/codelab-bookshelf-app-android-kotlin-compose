package com.example.bookshelf.di

import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.data.DefaultBookshelfRepository
import com.example.bookshelf.network.BookshelfApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

class DefaultAppContainer : AppContainer {
    // Notes: explicitNulls is needed for NULLABLE List
    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }
    @OptIn(ExperimentalSerializationApi::class)
    override val bookshelfApiService: BookshelfApiService by lazy {
        Retrofit.Builder()
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(json
                    .asConverterFactory("application/json".toMediaType()))
            .baseUrl(BookshelfApiService.BASE_URL)
            .build()
            .create()
    }

    override val bookshelfRepository: BookshelfRepository by lazy {
        DefaultBookshelfRepository(bookshelfApiService)
    }
}