package com.example.movieapp.navigation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.models.FavoritesViewModel
import com.example.movieapp.screens.DetailScreen
import com.example.movieapp.screens.FavoritesScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    val favoritesViewModel: FavoritesViewModel = viewModel()
    favoritesViewModel.favoriteMovies

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        //graph as trailing lambda

        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController, favoritesViewModel)
        }

        composable(
            route = MovieScreens.DetailScreen.name + "/{movieID}",
            arguments = listOf(navArgument("movieID") {
                type = NavType.StringType
            })
        ) {
            //get argument from backstack and pass as argument to detailscreen
                backStackEntry ->
            DetailScreen(
                movieID = backStackEntry.arguments?.getString("movieID"),
                navController = navController,
                viewModel = favoritesViewModel
            )
        }
        composable(MovieScreens.FavoritesScreen.name) {
            FavoritesScreen(
                navController = navController,
                viewModel = favoritesViewModel)
        }
    }
}