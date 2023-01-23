package com.example.bookshelf.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookshelf.R
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun NothingFoundScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_not_found),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            stringResource(R.string.no_records_found_msg),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun NothingFoundScreenPreview() {
    BookshelfTheme {
        NothingFoundScreen()
    }
}