package com.example.bookshelf.ui.screens.query_screen

data class SearchUiState(
    val query: String = "trump", // Notes: just because I am a lazy typer
    val searchStarted: Boolean = false
)
