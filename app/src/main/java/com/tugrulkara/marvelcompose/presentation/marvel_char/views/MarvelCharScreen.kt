package com.tugrulkara.marvelcompose.presentation.marvel_char.views

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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tugrulkara.marvelcompose.domain.model.MarvelChar
import com.tugrulkara.marvelcompose.presentation.marvel_char.MarvelCharViewModel

@Composable
fun MarvelCharScreen(
    navController: NavController,
    viewModel: MarvelCharViewModel= hiltViewModel()
) {
    
    val state=viewModel.state.value
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {
        
        Column() {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.marvelCharList){marvelChar->

                    MarvelCharRow(marvelChar = marvelChar, onItemClick = {

                    })

                }
            }
        }
    }
    
}

@Composable
fun MarvelCharRow(
    marvelChar: MarvelChar,
    onItemClick: (MarvelChar)->Unit) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClick(marvelChar)
        }
        .padding(10.dp)) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.CenterVertically)) {

            Image(painter = rememberAsyncImagePainter(model = marvelChar.thumbnail+".jpg"), contentDescription = marvelChar.name,
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxWidth()
                    .clip(RectangleShape))

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = marvelChar.name,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color= Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}