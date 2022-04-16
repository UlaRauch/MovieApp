package com.example.movieapp.screens


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.models.FavoritesViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel
) {
// state hoisting hier
    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp) {
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                            //Log.d(MovieScreens.DetailScreen.name, )
                        }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "My Favourite Movies")
                }
            }
        }) {
        val favourites = viewModel.favoriteMovies
        MainContent(favorites = favourites, navController = navController)
    }
}


@Composable
fun MainContent(favorites: List<Movie>, navController: NavController) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        LazyColumn {
            items(favorites) { movie ->
                MovieRow(
                    movie = movie,
                    showFavoriteButton = false,
                    onItemClick = {
                            movieID -> navController.navigate(MovieScreens.DetailScreen.name + "/$movieID")}) {
                }
            }
        }
    }
}
