package com.tugrulkara.marvelcompose.presentation.marvel_char.views

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tugrulkara.marvelcompose.domain.model.MarvelChar
import com.tugrulkara.marvelcompose.presentation.Screen
import com.tugrulkara.marvelcompose.presentation.marvel_char.MarvelCharViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MarvelCharScreen(
    navController: NavController,
    viewModel: MarvelCharViewModel= hiltViewModel()
) {

    val charList= remember { viewModel.charList }
    val state= remember { viewModel.state }

    Box(modifier = Modifier
        .fillMaxSize()) {

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
            if (state.value.isLoading){
                CircularProgressIndicator(color = Color.White)
            }
            if (state.value.errorMessage.isNotEmpty()){
                RetryView(error = state.value.errorMessage) {
                    viewModel.getMarvelChars()
                }
            }
        }

        Column {

            Text(
                text = viewModel.state.value.copyright,
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally))

            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ){
                viewModel.searchCharList(it)
            }

            LazyColumn(modifier = Modifier.fillMaxSize()){

                items(charList.value){marvelChar->
                    val encodedUrl = URLEncoder.encode(marvelChar.thumbnail, StandardCharsets.UTF_8.toString())
                    val textFromHtml = HtmlCompat.fromHtml(marvelChar.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    var textDesc=textFromHtml.toString()

                    if (textDesc.equals("") || textDesc.isEmpty()){
                        textDesc="None"
                    }

                    MarvelCharRow(marvelChar = marvelChar, onItemClick = {
                        navController.navigate(route = "${Screen.MarvelSeriesScreen.root}/${marvelChar.id}/${marvelChar.name}/${textDesc}/${encodedUrl}")
                    })

                }
            }

        }
    }

}


@Composable
fun SearchBar(
    modifier: Modifier=Modifier,
    hint: String="",
    onSearch: (String) -> Unit = {}
) {

    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text=it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier= Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
        if (isHintDisplayed){
            Text(
                text = hint,
                color = Color.LightGray,
                modifier= Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
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
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Box(
                    modifier = Modifier.height(200.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = marvelChar.thumbnail+".jpg"),
                        contentDescription = marvelChar.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
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
                            text = marvelChar.name,
                            style = TextStyle(color = Color.White, fontSize = 26.sp)
                        )
                    }
                }
            }

        }
    }

}

@Composable
fun RetryView(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onRetry() },
            colors = ButtonDefaults.buttonColors(Color.White)
            //modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry", color = Color.Red)
        }
    }
}