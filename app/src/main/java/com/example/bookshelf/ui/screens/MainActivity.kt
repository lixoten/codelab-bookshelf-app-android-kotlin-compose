package com.example.bookshelf.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.BookshelfApp
import com.example.bookshelf.ui.screens.home_screen.HomeViewModel
import com.example.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookshelfTheme {
                val viewModel : HomeViewModel = viewModel(factory = HomeViewModel.Factory)
                BookshelfApp(viewModel)
            }
        }
    }
}