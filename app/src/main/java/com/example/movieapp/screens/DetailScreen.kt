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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.HorizontalScrollableImageView
import com.example.movieapp.widgets.MovieRow


@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
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
                            navController.popBackStack() //gibt false zurück, screen bleibt, wie er ist
                            //Log.d(MovieScreens.DetailScreen.name, )
                        }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = movie.title)
                }
            }
        }) {
        MainContent(movie = movie)
    }
}

//in Homescreen hat die Homescreen funktion die Topappbar und Maincontent ruft nur die Movierow auf
@Composable
fun MainContent(movie: Movie) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column {
            MovieRow(movie = movie)
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
