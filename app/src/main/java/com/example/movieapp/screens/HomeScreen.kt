import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.movieapp.models.FavoritesViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: FavoritesViewModel
) {
    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = {
                            navController.navigate(route = MovieScreens.FavoritesScreen.name)
                        }) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorites",
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    text = "Favorites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp)
                                )
                            }
                        }
                    }
                }
            )
        }
    ) {
        MainContent(
            navController = navController,
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
    movieList: List<Movie> = getMovies(),
    navController: NavController,
    isFavoriteLambda: (Movie) -> Boolean,
    onFavoriteClick: (Movie) -> Unit = {}
) {
    //listOf("Harry Potter and the Philosopher's Stone", "Harry Potter and the Chamber of Secrets", "Harry Potter and the Prisoner of Azkaban")
    LazyColumn {
        items(movieList) { movie ->
            MovieRow(
                movie = movie,
                isFavorite = isFavoriteLambda(movie),
                showFavoriteButton = true,
                onFavoriteClick = onFavoriteClick,
                onItemClick = { movieID ->
                    navController.navigate("HomeScreen")
                    navController.navigate(MovieScreens.DetailScreen.name + "/$movieID")
                },
                /*
                content = {
                    favoriteButton(
                        movie = movie,
                        isFavorite = isFavoriteLambda(movie)
                    ) { movie ->
                        onFavoriteClick(movie)
                    }
                }

                 */

            )
        }
    }
}


