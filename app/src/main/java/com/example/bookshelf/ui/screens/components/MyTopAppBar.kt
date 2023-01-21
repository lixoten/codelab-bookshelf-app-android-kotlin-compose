package com.example.bookshelf.ui.screens.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.bookshelf.R

@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) }
    )
}