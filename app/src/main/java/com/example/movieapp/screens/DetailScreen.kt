package com.example.movieapp.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.models.FavoritesViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.widgets.HorizontalScrollableImageView
import com.example.movieapp.widgets.MovieRow


@Composable
fun DetailScreen(
    viewModel: FavoritesViewModel,
    navController: NavController,
    movieID: String? = "t0499549"
) {
    val movie = filterMovie(movieID = movieID)

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp) {
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
                    Text(text = movie.title)
                }
            }
        }) {
        MainContent(
            movie = movie,
            isFavoriteLambda = {
                    movie -> viewModel.movieIsFavorite(movie)
            }
            ) {
                movie -> if (viewModel.movieIsFavorite(movie)) {
            viewModel.removeMovie(movie)
        } else viewModel.addMovie(movie = movie)
        }
    }
}


@Composable
fun MainContent(
    movie: Movie,
    isFavoriteLambda: (Movie) -> Boolean,
    onFavoriteClick: (Movie) -> Unit = {}) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column {
            MovieRow(
                movie = movie,
                isFavorite = isFavoriteLambda(movie),
                onFavoriteClick = onFavoriteClick)
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = movie.title, style = MaterialTheme.typography.h5)
            HorizontalScrollableImageView(movie = movie)
        }
    }
}


fun filterMovie(movieID: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieID }[0]
}
