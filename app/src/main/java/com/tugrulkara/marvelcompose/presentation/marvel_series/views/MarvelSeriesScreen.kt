package com.tugrulkara.marvelcompose.presentation.marvel_series.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.tugrulkara.marvelcompose.domain.model.MarvelSeries
import com.tugrulkara.marvelcompose.presentation.marvel_series.MarvelSeriesViewModel

@Composable
fun MarvelSeriesScreen(
    viewModel: MarvelSeriesViewModel= hiltViewModel()) {

    val state=viewModel.state.value

    println(state.errorMessage)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {

        Column() {
            LazyColumn(modifier = Modifier.fillMaxSize()){

                items(state.marvelSeriesList){marvelSeries->
                    MarvelSeriesRow(marvelSeries = marvelSeries)
                }
            }
        }

    }

}

@Composable
fun MarvelSeriesRow(
    marvelSeries:MarvelSeries) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {

        Image(
            painter = rememberAsyncImagePainter(model = marvelSeries.thumbnail+".jpg"),
            contentDescription = marvelSeries.title,
            modifier = Modifier
                .padding(16.dp)
                .size(200.dp, 200.dp)
                .clip(RectangleShape))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {

            Text(
                text = marvelSeries.title,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color= Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}