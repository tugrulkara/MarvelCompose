package com.tugrulkara.marvelcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tugrulkara.marvelcompose.presentation.marvel_char.views.MarvelCharScreen
import com.tugrulkara.marvelcompose.presentation.marvel_series.views.MarvelSeriesScreen
import com.tugrulkara.marvelcompose.presentation.ui.theme.MarvelComposeTheme
import com.tugrulkara.marvelcompose.util.Constants.CHAR_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController= rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.MarvelCharScreen.root ){
                        composable(Screen.MarvelCharScreen.root){
                            MarvelCharScreen(navController = navController)
                        }
                        composable(
                            Screen.MarvelSeriesScreen.root+"/{${CHAR_ID}}"){
                            MarvelSeriesScreen()
                        }
                    }

                }
            }
        }
    }
}