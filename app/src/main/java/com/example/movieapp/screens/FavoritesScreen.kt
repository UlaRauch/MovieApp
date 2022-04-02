package com.example.movieapp.screens


import android.service.autofill.OnClickAction
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.HorizontalScrollableImageView
import com.example.movieapp.widgets.MovieRow

@Preview(showBackground = true)
@Composable
fun FavoritesScreen(
    navController: NavController = rememberNavController(),
) {

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
        val favourites = listOf<String>("tt0499549", "tt0416449")
        MainContent(favorites = favourites, navController = navController)
    }
}


@Composable
fun MainContent(favorites: List<String>, navController: NavController) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        LazyColumn {
            items(favorites) { id ->
                MovieRow(movie = filterMovie(id)) { movieID ->
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieID")
                }
            }
        }
    }
}
