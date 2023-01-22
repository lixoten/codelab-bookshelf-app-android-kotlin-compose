package com.example.bookshelf.ui.screens.detail_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.Book
import com.example.bookshelf.model.VolumeInfo
import com.example.bookshelf.ui.screens.home_screen.*
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    retryAction: () -> Unit,
) {
    val uiStateDet = viewModel.uiStateDetail.collectAsState().value

    when (uiStateDet) {

        is DetailsUiState.Loading -> {
            LoadingScreen()
        }
        is DetailsUiState.Error -> {
            ErrorScreen(
                retryAction = retryAction
            )
        }
        is DetailsUiState.Success -> {
            BookDetails(uiStateDet.bookItem)
        }
    }

}


@Composable
fun BookDetails(book: Book) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
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
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = book.volumeInfo.description,
                style = MaterialTheme.typography.body1
            )
        }
    }
}


// Notes: the reason we see the images as the are is because it is using the image placeholder in AsyncImage
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    BookshelfTheme {
        val mockData =
            Book(
                "1233123",
                volumeInfo = VolumeInfo(
                    title = "How to Poop!!!",
                    description = "An educational book on the proper way to poop.",
                    imageLinks = null,
                )
            )
        BookDetails(
            book = mockData,
        )
    }
}