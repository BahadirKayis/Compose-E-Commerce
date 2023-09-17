package com.bahadir.tostbangcase.presentation.screens.detail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.presentation.screens.detail.state.DetailUIEvent
import com.bahadir.tostbangcase.presentation.screens.detail.state.DetailUIState
import com.bahadir.tostbangcase.presentation.util.basecomponent.BottomTotal
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRoute(viewModel: DetailVM = hiltViewModel()) {
    val uiState by viewModel.state.collectAsState(initial = DetailUIState(isLoading = true))
    DetailScreen(uiState = uiState, viewModel = viewModel)
}

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(uiState: DetailUIState, viewModel: DetailVM) {
    uiState.uiData?.let { product ->
        ProductDetail(item = product, viewModel)
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun ProductDetail(item: FiriyaUI, viewModel: DetailVM) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    androidx.compose.material.Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
    ) { _ ->
        Column(
            Modifier.fillMaxSize(),

        ) {
            AsyncImage(
                model = item.image,
                contentDescription = item.title,
                modifier = Modifier.fillMaxWidth().background(Color.White).weight(0.5f),
            )
            Column(
                Modifier.fillMaxWidth().weight(0.4f),
            ) {
                val textHeight = remember {
                    mutableDoubleStateOf(0.1)
                }
                Text(
                    text = item.title,
                    fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                    fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
                    fontSize = 19.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 6.dp, bottom = 6.dp)
                        .weight(textHeight.doubleValue.toFloat()),

                    textAlign = TextAlign.Start,
                    onTextLayout = { result ->
                        if (result.didOverflowHeight) {
                            textHeight.doubleValue += 0.1
                        }
                    },

                )

                Text(
                    text = item.description,
                    modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)
                        .weight(1f - textHeight.doubleValue.toFloat()),
                    fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                )
            }

            Row(
                Modifier.fillMaxSize().weight(0.1f).align(alignment = Alignment.CenterHorizontally)
                    .background(Color(0xffF4F4F4)),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,

            ) {
                BottomTotal(
                    item.price.toDouble(),
                    priceName = R.string.price,
                    buttonName = R.string.add_basket,
                ) {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Sepete Eklendi",
                        )
                    }
                    viewModel.setEvent(DetailUIEvent.AddProduct(item))
                }
            }
        }
    }
}
