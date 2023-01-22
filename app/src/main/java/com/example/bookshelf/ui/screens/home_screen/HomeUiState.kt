package com.example.bookshelf.ui.screens.home_screen

import com.example.bookshelf.model.Book

sealed interface HomeUiState {
    data class Success(val bookshelfList: List<Book>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}