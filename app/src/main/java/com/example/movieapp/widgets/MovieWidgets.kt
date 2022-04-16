package com.example.movieapp.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    //isFavorite: Boolean = false,
    //showFavoriteButton: Boolean = true,
    onItemClick: (String) -> Unit = {},
    //onFavoriteClick: (Movie) -> Unit = {},
    favoriteButton: Unit = FavoriteButton(show = false, movie = movie)
    //nur ein versuch, noch weiter anschaun
) {
    var showInfo by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //.height(130.dp) //remove this to keep height flexible
            .clickable {
                showInfo = !showInfo
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {
            Surface(
                modifier = Modifier
                    .padding(4.dp)
                    .size(100.dp),
                elevation = 4.dp
            ) {
                // Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie pic")
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = "round image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                )
            }
            Column() {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}")
                Text(text = "Released: ${movie.year}")
                AnimatedVisibility(visible = showInfo) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Plot: ${movie.plot}")
                        Divider(
                            modifier = Modifier.padding(3.dp),
                            color = Color.LightGray
                        ) //horizontal grey line in screenshot
                        Text(text = "Genre: ${movie.genre}")
                        Text(text = "Actors: ${movie.actors}")
                        Text(text = "Rating: ${movie.rating}")
                    }
                }
                IconButton(onClick = { showInfo = !showInfo }) {
                    if (showInfo) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "moreInfo",
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "lessInfo"
                        )
                    }
                }
            }
            Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End) {

                favoriteButton
                /*
                if (showFavoriteButton) {
                    FavoriteButton(
                        movie = movie,
                        isFavorite = isFavorite
                    ) { movie ->
                        onFavoriteClick(movie)
                    }

                }

                 */
            }

        }
    }
}

@Composable
fun FavoriteButton(
    show: Boolean = true,
    movie: Movie,
    isFavorite: Boolean = false,
    onFavoriteClick: (Movie) -> Unit = {}
) {

Log.i("Mainactivity", "favoriteButton called")
    if (show) {

        IconButton(onClick = {
            onFavoriteClick(movie)
        }) {
            if (!isFavorite) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "not a favorite",
                    //modifier = Modifier
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "is a favorite",
                    //modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {
                AsyncImage(model = image, contentDescription = "movie image gallery")
            }
        }
    }
}