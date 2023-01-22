package com.example.bookshelf.ui.screens.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookshelfRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DetailsViewModel(
    private val bookshelfRepository: BookshelfRepository
): ViewModel() {
    private val _uiStateDetail = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiStateDetail = _uiStateDetail.asStateFlow()


    fun getBook(id: String) {
        viewModelScope.launch {
            _uiStateDetail.value = try {
                // Notes: List<Book>? NULLABLE
                val book = bookshelfRepository.getBook(id)
                if (book == null) {
                    DetailsUiState.Error
                } else{
                    DetailsUiState.Success(book)
                }
            } catch (e: IOException) {
                DetailsUiState.Error
            } catch (e: HttpException) {
                DetailsUiState.Error
            }
        }
    }

    /**
     * Factory for BookshelfViewModel] that takes BookshelfRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.container.bookshelfRepository
                DetailsViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}