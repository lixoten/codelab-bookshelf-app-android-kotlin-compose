package com.example.bookshelf.ui.screens.home_screen

import com.example.bookshelf.model.Book

sealed interface BookshelfUiState {
    data class Success(val bookshelfList: List<Book>) : BookshelfUiState
    object Error : BookshelfUiState
    object Loading : BookshelfUiState
}