package com.example.bookshelf.ui.screens.query_screen

import com.example.bookshelf.model.Book

sealed interface QueryUiState {
    data class Success(val bookshelfList: List<Book>) : QueryUiState
    object Error : QueryUiState
    object Loading : QueryUiState
}