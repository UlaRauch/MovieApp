package com.example.movieapp.navigation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.DetailScreen
import com.example.movieapp.screens.FavoritesScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        //graph as trailing lambda
        composable(MovieScreens.HomeScreen.name) { HomeScreen(navController = navController) }
        composable(
            route = MovieScreens.DetailScreen.name + "/{movieID}",
            arguments = listOf(navArgument("movieID") {
                type = NavType.StringType
            })
        ) {
                //get argument from backstack and pass as argument to detailscreen
                backStackEntry ->
                DetailScreen(movieID = backStackEntry.arguments?.getString("movieID"))
        }
        composable(MovieScreens.FavoritesScreen.name) {
            FavoritesScreen(navController = navController)
        }
    }
}