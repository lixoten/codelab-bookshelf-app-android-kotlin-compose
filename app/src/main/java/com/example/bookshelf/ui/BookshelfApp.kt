package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookshelf.ui.screens.components.MyTopAppBar
import com.example.bookshelf.ui.screens.home_screen.BookshelfViewModel
import com.example.bookshelf.ui.screens.home_screen.HomeScreen

@Composable
fun BookshelfApp(
    viewModel: BookshelfViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { MyTopAppBar() }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            HomeScreen(
                viewModel = viewModel,
                retryAction = { viewModel.getBooks() }
            )
        }
    }
}