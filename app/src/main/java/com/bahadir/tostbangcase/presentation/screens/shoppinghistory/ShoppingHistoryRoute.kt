package com.bahadir.tostbangcase.presentation.screens.shoppinghistory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.core.extensions.formatPrice
import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.presentation.util.ScreenState
import com.bahadir.tostbangcase.presentation.util.StateError
import com.bahadir.tostbangcase.presentation.util.StateLoading

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ShoppingHistoryRoute(viewModel: SoldHistoryVM = hiltViewModel()) {
    val uiState by viewModel.screenState.collectAsState(initial = ScreenState.Loading)

    ShoppingHistoryScreen(uiState)
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ShoppingHistoryScreen(uiState: ScreenState<List<FiriyaSoldBasket>>) {
    when (uiState) {
        is ScreenState.Loading -> {
            StateLoading()
        }

        is ScreenState.Error -> {
            StateError()
        }

        is ScreenState.Success -> {
            ShoppingHistoryList(uiState.uiData)
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ShoppingHistoryList(firiyaSoldBasket: List<FiriyaSoldBasket>) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        firiyaSoldBasket.forEach {
            stickyHeader {
                Text(
                    "Buy Date : ${it.date}", color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF6650a4))
                        .padding(16.dp),
                )
            }

            items(it.firiyaItem) {
                ShoppingHistoryItem(it)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ShoppingHistoryItem(firiyaItem: FiriyaUI) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),

    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = firiyaItem.image,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),

            ) {
                Text(
                    text = firiyaItem.title,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.5f),

                ) {
                    Text(
                        text = "Buy Count: ${firiyaItem.count}",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(0.5f)
                            .padding(top = 8.dp),

                    )

                    Text(
                        text = stringResource(
                            id = R.string.price,
                            (firiyaItem.price.toDouble() * firiyaItem.count).formatPrice(),
                        ),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center, // Metni sağa hizalayın
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(top = 8.dp)
                            .align(Alignment.CenterVertically),

                    )
                }
            }
        }
    }
}
