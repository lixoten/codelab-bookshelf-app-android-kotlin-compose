package com.example.bookshelf

import android.app.Application
import com.example.bookshelf.di.AppContainer
import com.example.bookshelf.di.DefaultAppContainer

class BookshelfApplication: Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}