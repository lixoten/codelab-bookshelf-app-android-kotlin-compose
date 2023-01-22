package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.AppDestinations
import com.example.bookshelf.BookshelfNavHost
import com.example.bookshelf.ui.screens.components.MyTopAppBar
import com.example.bookshelf.ui.screens.home_screen.HomeViewModel

@Composable
fun BookshelfApp(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    // Notes: Set Nav Controller
    val navController = rememberNavController()
    // Notes: Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Notes: Get the name of the current screen check for null
    val currentScreen = AppDestinations.valueOf(
        backStackEntry?.destination?.route ?: AppDestinations.HomeScreen.name
    )

    // Notes: Boolean to check if we can nagigate back. Check stack
    val canNavigateBack = navController.previousBackStackEntry != null


    Scaffold(
        topBar = {
            MyTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                onNavigateUpClicked = { navController.navigateUp() }
            )
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            BookshelfNavHost(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }
    }
}