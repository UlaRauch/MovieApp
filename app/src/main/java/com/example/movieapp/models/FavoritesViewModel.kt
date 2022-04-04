package com.example.movieapp.models

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class FavoritesViewModel: ViewModel() {
    private val _favoriteMovies = mutableStateListOf<Movie>()
    val favoriteMovies: List<Movie>
    get() = _favoriteMovies


    fun addMovie(movie: Movie) {
            _favoriteMovies.add(movie)
            Log.i("Mainactivity", "movie added")
    }

    fun removeMovie(movie: Movie) {
        _favoriteMovies.remove(movie)
        Log.i("Mainactivity", "movie removed")

    }

    fun movieIsFavorite(movie: Movie): Boolean {
        return _favoriteMovies.contains(movie)
    }
}