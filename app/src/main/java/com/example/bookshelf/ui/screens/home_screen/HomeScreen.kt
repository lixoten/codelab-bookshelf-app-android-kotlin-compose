package com.example.bookshelf.ui.screens.home_screen

import android.view.KeyEvent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.Book
import com.example.bookshelf.model.VolumeInfo
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: BookshelfViewModel,
    retryAction: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState().value
    val uiStateQuery = viewModel.uiStateSearch.collectAsState().value

    val focusManager = LocalFocusManager.current
    // Notes:  using viewModel to hold the state of query
    //var query by rememberSaveable {
    //    mutableStateOf("")
    //}

    Column {
        OutlinedTextField(
            value = uiStateQuery.query,
            onValueChange = { viewModel.updateQuery(it) },
            singleLine = true,
            placeholder = {
                Text(text = stringResource(R.string.search_placeholder))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    viewModel.getBooks(uiStateQuery.query)
                }
            ),
            modifier = Modifier
                .onKeyEvent { e ->
                    if (e.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                        focusManager.clearFocus()
                        viewModel.getBooks(uiStateQuery.query)
                    }
                    false
                }
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        )

        when (uiState) {
            is BookshelfUiState.Loading -> LoadingScreen(modifier)
            is BookshelfUiState.Success -> GridScreen(
                bookshelfList = uiState.bookshelfList,
                modifier = modifier
            )
            // Notes: This was to display a List of books instead of Grid
            // is BookshelfUiState.Success -> ListScreen(
            //     bookshelfList = uiState.bookshelfList,
            //     modifier = modifier
            // )
            is BookshelfUiState.Error ->
                ErrorScreen(retryAction = retryAction, modifier)
        }
    }
}




// Notes: This was to display a List of books instead of Grid
@Composable
private fun ListScreen(
    bookshelfList: List<Book>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(24.dp),
    ) {
        items(
            items = bookshelfList,
            key = {
                it.id
            }
        ) {
            ListItem(book = it)
        }
    }
}

// Notes: This was to display a List of books instead of Grid
@Composable
fun ListItem(book: Book) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, Color.Green),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.h4
            )
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.volumeInfo.imageLinks?.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = book.volumeInfo.title,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
            )
        }
    }
}

// Notes: the reason we see the images as the are is because it is using the image placeholder in AsyncImage
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    BookshelfTheme {
        val mockData = List(10) {
            Book(
                "Lorem Ipsum - $it",
                volumeInfo = VolumeInfo(
                    title = "xxx $it",
                    description = "xxx $it",
                    imageLinks = null,
                )
            )
        }
        GridScreen(
            bookshelfList = mockData,
            modifier = Modifier
        )
    }
}