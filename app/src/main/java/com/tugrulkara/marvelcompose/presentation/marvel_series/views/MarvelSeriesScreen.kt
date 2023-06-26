package com.tugrulkara.marvelcompose.presentation.marvel_series.views

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tugrulkara.marvelcompose.domain.model.MarvelSeries
import com.tugrulkara.marvelcompose.presentation.marvel_series.MarvelSeriesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelSeriesScreen(
    name:String,
    desc:String,
    image:String,
    navController: NavController,
    viewModel: MarvelSeriesViewModel= hiltViewModel()) {

    val context = LocalContext.current

    val state = remember { viewModel.state }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors=TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.background),
                title = {
                    Text(
                        name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back description"
                        )
                    }
                }
            )

        }
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {

            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
                if (state.value.isLoading){
                    CircularProgressIndicator(color = Color.White)
                }
                if (state.value.errorMessage.isNotEmpty()){
                    println("Error")
                }
            }

            Column {

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                    shape = RoundedCornerShape(10.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(model = image+".jpg"),
                        contentDescription = name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize())
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier
                        .padding(15.dp))
                Spacer(modifier = Modifier.padding(7.dp))

                Text(
                    text = desc,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier
                        .padding(15.dp))

                LazyRow(modifier = Modifier.fillMaxSize()){


                    items(state.value.marvelSeriesList){marvelSeries->

                        if (state.value.marvelSeriesList.isNotEmpty()){
                            MarvelSeriesRow(marvelSeries = marvelSeries, onItemClick = {
                                val openURL = Intent(Intent.ACTION_VIEW)
                                openURL.data = Uri.parse(marvelSeries.urls[0].url)
                                context.startActivity(openURL)
                            })
                        }

                    }
                }
            }

        }
    }


}

@Composable
fun MarvelSeriesRow(
    marvelSeries:MarvelSeries,
    onItemClick: (MarvelSeries)->Unit) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClick(marvelSeries)
        }
        .padding(20.dp)) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {

            Card(
                modifier = Modifier
                    .size(130.dp,180.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Box() {
                    Image(
                        painter = rememberAsyncImagePainter(model = marvelSeries.thumbnail+".jpg"),
                        contentDescription = marvelSeries.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(130.dp,150.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    ),
                                    startY = 50f
                                )
                            )
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            text = marvelSeries.title,
                            style = androidx.compose.ui.text.TextStyle(
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }
    }

}