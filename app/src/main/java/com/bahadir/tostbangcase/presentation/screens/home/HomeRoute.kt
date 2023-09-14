package com.bahadir.tostbangcase.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.presentation.navigation.MainScreen
import com.bahadir.tostbangcase.presentation.util.ScreenState
import com.bahadir.tostbangcase.presentation.util.StateError
import com.bahadir.tostbangcase.presentation.util.StateLoading
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun HomeRoute(
    navController: NavHostController,
    onProductClicked: (FiriyaUI) -> Unit,
    viewModel: HomeVM = hiltViewModel(),
) {
    val uiState by viewModel.screenState.collectAsState(initial = ScreenState.Loading)
    HomeScreen(
        uiState = uiState,
        onProductClicked = onProductClicked,
        navController = navController,
    )
}

@Composable
fun HomeScreen(
    uiState: ScreenState<List<FiriyaUI>>,
    onProductClicked: (FiriyaUI) -> Unit,
    navController: NavHostController,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MainScreen(navController = navController, Modifier.navigationBarsPadding()) },
    ) { paddingvalues ->
        Surface(
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = paddingvalues.calculateBottomPadding()),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                when (uiState) {
                    is ScreenState.Error -> {
                        StateError(uiState.message)
                    }

                    ScreenState.Loading -> {
                        StateLoading()
                    }

                    is ScreenState.Success -> {
                        ProductList(
                            productList = uiState.uiData,
                            onProductClicked = onProductClicked,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductList(productList: List<FiriyaUI>, onProductClicked: (FiriyaUI) -> Unit) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),

    ) {
        items(productList) { firiya ->

            ProductItem(item = firiya, onProductClicked = onProductClicked)
        }
    }
}

@Composable
fun ProductItem(item: FiriyaUI, onProductClicked: (FiriyaUI) -> Unit) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(180.dp),
        shape = RoundedCornerShape(8.dp),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .clickable { onProductClicked(item) },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Transparent),
            )

            Text(
                text = item.title,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,

            ) {
                Text(
                    text = stringResource(id = R.string.price, item.price),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Red,
                    textAlign = TextAlign.End,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prev() {
}
