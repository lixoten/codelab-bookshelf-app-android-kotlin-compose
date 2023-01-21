package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.BookshelfApp
import com.example.bookshelf.ui.screens.home_screen.BookshelfViewModel
import com.example.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookshelfTheme {
                val viewModel : BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
                BookshelfApp(viewModel)
            }
        }
    }
}