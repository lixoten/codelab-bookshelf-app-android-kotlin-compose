package com.example.bookshelf.ui.screens.query_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class QueryViewModel(
    private val bookshelfRepository: BookshelfRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<QueryUiState>(QueryUiState.Loading)
    val uiState = _uiState.asStateFlow()

    var selectedBookId by mutableStateOf("")

    private val _uiStateSearch = MutableStateFlow(SearchUiState())
    val uiStateSearch = _uiStateSearch.asStateFlow()


    // Notes: Question: I would like this to go to a separate viewModel since it belangs to a
    //  different screen. but at moment I am not sure how to get it donem because to
    //  select a favority book I would need to pass both view models
    // Logic for Favorite books -- Beg
    var favoriteBooks: MutableList<Book> by mutableStateOf(mutableListOf<Book>())
        private set


    var favoritesfUiState: QueryUiState by mutableStateOf(QueryUiState.Loading)
        private set


    fun isBookFavorite(book: Book): Boolean {
        return !favoriteBooks.filter { x -> x.id == book.id }.isEmpty()
    }


    fun addFavoriteBook(book: Book) {
        if (!isBookFavorite(book)) {
            favoriteBooks.add(book)
            favoritesUpdated()
        }
    }

    fun removeFavoriteBook(book: Book) {
        favoriteBooks.removeIf { it.id == book.id }
        favoritesUpdated()
    }


    private fun favoritesUpdated() {
        viewModelScope.launch {
            favoritesfUiState = QueryUiState.Loading
            favoritesfUiState = QueryUiState.Success(favoriteBooks)

        }
    }
    // Logic for Favorite books -- End


    fun updateQuery(query: String){
        _uiStateSearch.update { currentState ->
            currentState.copy(
                query = query
            )
        }
    }

    fun updateSearchStarted(searchStarted: Boolean){
        _uiStateSearch.update { currentState ->
            currentState.copy(
                searchStarted = searchStarted
            )
        }
    }

    fun getBooks(query: String = "") { //  "travel"
        updateSearchStarted(true)
        viewModelScope.launch {
            _uiState.value = QueryUiState.Loading

            _uiState.value = try {
                // Notes: List<Book>? NULLABLE
                val books = bookshelfRepository.getBooks(query)
                if (books == null) {
                    QueryUiState.Error
                } else if (books.isEmpty()){
                    QueryUiState.Success(emptyList())
                } else{
                    QueryUiState.Success(books)
                }
            } catch (e: IOException) {
                QueryUiState.Error
            } catch (e: HttpException) {
                QueryUiState.Error
            }
        }
    }


    // Notes: Question: At moment this is chuck of code is repeated in two files
    //  in QueryViewModel and in DetailsViewModel.
    //  what can I do/ place it so as not to have repeat code? I tried but I got a bunch of errors
    /**
     * Factory for BookshelfViewModel] that takes BookshelfRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.container.bookshelfRepository
                QueryViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}