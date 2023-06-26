package com.tugrulkara.marvelcompose.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tugrulkara.marvelcompose.presentation.marvel_char.views.MarvelCharScreen
import com.tugrulkara.marvelcompose.presentation.marvel_series.views.MarvelSeriesScreen
import com.tugrulkara.marvelcompose.presentation.ui.theme.MarvelComposeTheme
import com.tugrulkara.marvelcompose.util.Constants.CHAR_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelComposeTheme {
                val navController= rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {

                    NavHost(navController = navController, startDestination = Screen.MarvelCharScreen.root ){
                        composable(Screen.MarvelCharScreen.root){
                            MarvelCharScreen(navController = navController)
                        }
                        composable(route = "${Screen.MarvelSeriesScreen.root}/{${CHAR_ID}}/{charName}/{charDescription}/{charImage}"){
                            val charName= remember {
                                it.arguments?.getString("charName")
                            }
                            val charDescription= remember {
                                it.arguments?.getString("charDescription")
                            }
                            val charImage= remember {
                                it.arguments?.getString("charImage")
                            }
                            MarvelSeriesScreen(
                                charName ?: "",
                                charDescription?: "",
                                charImage?: "",
                                navController)
                        }
                    }

                }
            }
        }
    }
}