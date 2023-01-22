package com.example.bookshelf

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookshelf.ui.screens.detail_screen.DetailScreen
import com.example.bookshelf.ui.screens.detail_screen.DetailsViewModel
import com.example.bookshelf.ui.screens.home_screen.HomeScreen

@Composable
fun BookshelfNavHost(
    viewModel: com.example.bookshelf.ui.screens.home_screen.HomeViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.HomeScreen.name,
        modifier = modifier
    ) {
        composable(route = AppDestinations.HomeScreen.name) {
            HomeScreen(
                viewModel = viewModel,
                retryAction = { viewModel.getBooks() },
                onDetailsClick = {
                    viewModel.selectedBookId = it.id
                    navController.navigate(AppDestinations.DetailScreen.name)
                }
            )
        }
        composable(route = AppDestinations.DetailScreen.name) {
            val detailViewModel : DetailsViewModel = viewModel(factory = DetailsViewModel.Factory)
            //detailViewModel.selectedBookIdXX = it.id
            detailViewModel.getBook(viewModel.selectedBookId)

            DetailScreen(
                viewModel = detailViewModel,
                retryAction = { detailViewModel.getBook(viewModel.selectedBookId) },
            )
        }
    }
}