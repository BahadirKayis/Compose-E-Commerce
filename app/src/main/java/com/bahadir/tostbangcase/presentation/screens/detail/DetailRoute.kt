package com.bahadir.tostbangcase.presentation.screens.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.presentation.util.ScreenState
import com.bahadir.tostbangcase.presentation.util.StateError
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRoute(viewModel: DetailVM = hiltViewModel()) {
    val uiState by viewModel.screenState.collectAsState(initial = ScreenState.Loading)
    DetailScreen(uiState = uiState) {
        viewModel.addFiriya(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(uiState: ScreenState<FiriyaUI>?, onBasketClick: (FiriyaUI) -> Unit) {
    when (uiState) {
        is ScreenState.Error -> {
            StateError(message = R.string.error_message)
        }

        ScreenState.Loading -> {
            Log.e("DetailScreen", "DetailScreen: Loading")
        }

        is ScreenState.Success -> {
            FiriyaDetail(item = uiState.uiData, onBasketClick = onBasketClick)
        }

        else -> Unit
    }
}

@ExperimentalMaterial3Api
@Composable
fun FiriyaDetail(item: FiriyaUI, onBasketClick: (FiriyaUI) -> Unit) {
    val scoffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    androidx.compose.material.Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scoffoldState,
    ) { _ ->
        Column(
            Modifier.fillMaxSize().background(Color(0xFFEEEEEE)),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = item.title,
                modifier = Modifier.fillMaxWidth().background(Color.White).weight(0.5f),
            )
            Column(
                Modifier.fillMaxWidth().weight(0.4f).padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                val textHeigh = remember {
                    mutableStateOf(0.1)
                }
                Text(
                    text = item.title,
                    fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                    fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth().weight(textHeigh.value.toFloat()),

                    textAlign = TextAlign.Start,
                    onTextLayout = { result ->
                        if (result.didOverflowHeight) {
                            textHeigh.value = 0.2
                        }
                    },

                )
                Text(
                    text = item.description,
                    modifier = Modifier.fillMaxWidth().weight(1f - textHeigh.value.toFloat()),
                    fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Start,
                    color = Color(0xC37E7E7E),
                )
            }

            Row(
                Modifier.fillMaxSize().weight(0.1f).align(alignment = Alignment.CenterHorizontally)
                    .background(Color(0xffF4F4F4)),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,

            ) {
                Box(
                    modifier = Modifier.fillMaxHeight().weight(0.5f),
                ) {
                    Text(
                        text = stringResource(id = R.string.price, item.price),
                        modifier = Modifier.align(alignment = Alignment.CenterEnd).padding(8.dp),
                        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        fontSize = 20.sp,
                        color = Color(0xFF6650a4),
                    )
                }
                Card(
                    modifier = Modifier.fillMaxHeight().weight(0.5f).padding(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        coroutineScope.launch {
                            scoffoldState.snackbarHostState.showSnackbar(
                                message = "Sepete Eklendi",
                            )
                        }
                        onBasketClick(item)
                    },

                ) {
                    Box(
                        modifier = Modifier.fillMaxSize().background(Color(0xFF6650a4)),

                    ) {
                        Text(
                            text = "Sepete Ekle",
                            fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            fontSize = 20.sp,
                            modifier = Modifier.align(alignment = Alignment.Center),

                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}
