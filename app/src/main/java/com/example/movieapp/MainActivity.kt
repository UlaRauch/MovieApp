package com.example.movieapp

import HomeScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                //val movies = listOf("Princess Mononoke", "Days in Heaven", "Pan's Labyrinth")
                MovieNavigation()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("Mainactivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Mainactivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Mainactivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Mainactivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Mainactivity", "onDestroy called")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        val movies = listOf("Princess Mononoke", "Days in Heaven", "Pan's Labyrinth")
        MovieNavigation()
    }
}