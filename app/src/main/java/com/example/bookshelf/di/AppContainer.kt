package com.example.bookshelf.di

import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.network.BookshelfApiService

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val bookshelfApiService: BookshelfApiService
    val bookshelfRepository: BookshelfRepository
}