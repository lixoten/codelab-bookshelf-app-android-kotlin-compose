package com.example.bookshelf.ui.screens.detail_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.Book
import com.example.bookshelf.ui.screens.components.ErrorScreen
import com.example.bookshelf.ui.screens.components.LoadingScreen

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
        //border = BorderStroke(2.dp, Color.Green),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Title: " + book.volumeInfo.title,
                style = MaterialTheme.typography.titleLarge
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
                text = stringResource(R.string.book_subtitle, book.volumeInfo.subtitle),
                style = MaterialTheme.typography.titleMedium
            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "publisher: " + book.volumeInfo.publisher,
//                style = MaterialTheme.typography.titleMedium
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "publishedDate: " + book.volumeInfo.publishedDate,
//                style = MaterialTheme.typography.titleMedium
//            )
//            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.book_authors, book.volumeInfo.allAuthors()),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.book_price, book.saleInfo.getPrice2),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "country: " + book.saleInfo.country,
                style = MaterialTheme.typography.titleMedium
            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "isEbook: " + book.saleInfo.isEbook,
//                style = MaterialTheme.typography.titleMedium
//            )

//            Spacer(modifier = Modifier.height(8.dp))
//            if (!book.getPrice().isEmpty()) {
//                Text(
//                    text = "listPrice: " + book.saleInfo.listPrice?.amount.toString() + " - " + book.saleInfo.listPrice?.currency,
//                    style = MaterialTheme.typography.titleMedium
//                )
//            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "listPrice: " + book.saleInfo.getPrice2,
                style = MaterialTheme.typography.titleMedium
            )


//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "description: " + book.description,
//                style = MaterialTheme.typography.bodyMedium
//            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "description: " + book.volumeInfo.description,
                style = MaterialTheme.typography.bodyMedium
            )


//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "smallThumbnail: " + book.volumeInfo.imageLinks?.smallThumbnail,
//                style = MaterialTheme.typography.titleMedium
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "thumbnail: " + book.volumeInfo.imageLinks?.thumbnail,
//                style = MaterialTheme.typography.titleMedium
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "httpsThumbnail: " + book.volumeInfo.imageLinks?.httpsThumbnail,
//                style = MaterialTheme.typography.titleMedium
//            )
        }
    }
}


//// Notes: the reason we see the images as the are is because it is using the image placeholder in AsyncImage
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DetailsScreenPreview() {
//    BookshelfTheme {
//        val mockData =
//            Book(
//                id = "123",
//                volumeInfo = VolumeInfo(
//                    title = "A book",
//                    description = "Caniss ortum, tanquam bassus exemplar.",
//                    publishedDate = "11/11/2011",
//                    authors =  listOf("AAA","aaa"),
//                    publisher = "John Carter",
//                    subtitle = "Cunu litist",
//                    imageLinks = null,
//                ),
//                saleInfo = SaleInfo(
//                    country = "USA",
//                    isEbook = false,
//                    listPrice = ListPrice(
//                        amount = 2.22f,
//                        currency = "US Dollar"
//                    )
//                )
//            )
//        BookDetails(
//            book = mockData,
//        )
//    }
//}