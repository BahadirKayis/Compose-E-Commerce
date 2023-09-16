package com.bahadir.tostbangcase.presentation.screens.basket

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.core.ProductState
import com.bahadir.tostbangcase.core.extensions.formatPrice
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.presentation.navigation.MainScreen
import com.bahadir.tostbangcase.presentation.util.ScreenState
import com.bahadir.tostbangcase.presentation.util.addAnimationSlideHorizontal
import com.bahadir.tostbangcase.presentation.util.addAnimationSlideVertical
import com.bahadir.tostbangcase.presentation.util.removeAnimationSlideHorizontal
import com.bahadir.tostbangcase.presentation.util.removeAnimationSlideVertical
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun BasketRoute(
    navController: NavHostController,
    viewModel: BasketVM = hiltViewModel(),
    navigateToDetail: (FiriyaUI) -> Unit,
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.screenState.collectAsState(initial = ScreenState.Loading)
    val scoffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    BasketScreen(
        uiState = uiState,
        navigateToDetail = navigateToDetail,
        navigateToHome = { cardName, list ->
            coroutineScope.launch {
                val job = coroutineScope.launch {
                    scoffoldState.snackbarHostState.showSnackbar(
                        message = "$cardName payment successful",
                        duration = SnackbarDuration.Indefinite,
                    )
                }
                delay(1000)
                navigateToHome.invoke()
                job.cancel()
                viewModel.soldProduct(list)
            }
        },
        updateBasket = { firiya, state ->
            viewModel.updateProduct(firiya, state)
        },
        scoffoldState = scoffoldState, coroutineScope, navController,
    )
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun BasketScreen(
    uiState: ScreenState<List<FiriyaUI>>,
    navigateToDetail: (FiriyaUI) -> Unit,
    navigateToHome: (String, List<FiriyaUI>) -> Unit,
    updateBasket: (FiriyaUI, ProductState) -> Unit,
    scoffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
) {
    val totalCount = remember {
        mutableStateOf(0.0)
    }

    when (uiState) {
        is ScreenState.Error -> {
            Text(text = "Error")
        }

        is ScreenState.Loading -> {
            Text(text = "Loading")
        }

        is ScreenState.Success -> {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scoffoldState,
                bottomBar = {
                    MainScreen(
                        navController = navController,
                        Modifier.navigationBarsPadding(),
                    )
                },

            ) { paddingValue ->
                Surface(
                    color = Color(0xffededed),
                    modifier = Modifier.padding(bottom = paddingValue.calculateBottomPadding()),
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.9f),
                        ) {
                            if (uiState.uiData.isNotEmpty()) {
                                BasketList(
                                    uiState.uiData,
                                    navigateToDetail,
                                    totalCount,
                                    updateBasket,
                                )
                            } else {
                                Text(
                                    text = "Basket is empty",
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.1f),
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            BottomTotal(totalCount, navigateToHome, uiState.uiData, coroutineScope)
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun BasketList(
    firiyaItem: List<FiriyaUI>,
    navigateToDetail: (FiriyaUI) -> Unit,
    totalCount: MutableState<Double>,
    updateBasket: (FiriyaUI, ProductState) -> Unit,
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),

    ) {
        items(firiyaItem) {
            BasketItem(it, navigateToDetail, totalCount, updateBasket)
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun BasketItem(
    firiyaItem: FiriyaUI,
    navigateToDetail: (FiriyaUI) -> Unit,
    totalCount: MutableState<Double>,
    updateBasket: (FiriyaUI, ProductState) -> Unit,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    val height by animateDpAsState(
        if (isExpanded) 175.dp else 100.dp,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing), label = "",
    )
    var oldCountItem by remember { mutableStateOf(firiyaItem.count) }

    LaunchedEffect(firiyaItem.count) {
        var diff = firiyaItem.count - oldCountItem
        if (diff == 0) diff = firiyaItem.count
        totalCount.value += (diff * firiyaItem.price.toDouble())
        oldCountItem = firiyaItem.count
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            isExpanded = isExpanded.not()
        },

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
                Row(
                    Modifier
                        .fillMaxSize()
                        .weight(0.4f),
                ) {
                    Text(
                        text = firiyaItem.title,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.7f),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(0.3f),

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(0.5f),

                        ) {
                            Row(
                                modifier = Modifier.weight(0.5f),

                            ) {
                                SmallFloatingActionButton(
                                    onClick = {
                                        updateBasket.invoke(firiyaItem, ProductState.REMOVE)
                                    },
                                    containerColor = Color.Red,
                                    contentColor = Color.White,
                                    modifier = Modifier
                                        .size(25.dp)
                                        .align(Alignment.Bottom),
                                ) {
                                    Icon(
                                        ImageVector.vectorResource(id = R.drawable.ic_minues),
                                        "remove item",
                                    )
                                }
                                Box(
                                    modifier = Modifier.weight(0.2f).fillMaxWidth()
                                        .align(Alignment.Bottom),
                                ) {
                                    AnimatedContent(
                                        targetState = firiyaItem.count,
                                        transitionSpec = {
                                            if (targetState > oldCountItem) {
                                                addAnimationSlideHorizontal().using(
                                                    SizeTransform(clip = false),
                                                )
                                            } else {
                                                removeAnimationSlideHorizontal().using(
                                                    SizeTransform(clip = false),
                                                )
                                            }
                                        },
                                        label = "ss",
                                    ) { count ->

                                        Text(
                                            text = count.toString(),
                                            style = MaterialTheme.typography.headlineSmall,
                                            textAlign = TextAlign.Center,
                                            fontSize = 16.sp,
                                            color = MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.8f,
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()


                                        )
                                    }
                                }

                                SmallFloatingActionButton(
                                    onClick = {
                                        updateBasket.invoke(firiyaItem, ProductState.ADD)
                                    },
                                    containerColor = Color.Green,
                                    contentColor = Color.White,
                                    modifier = Modifier
                                        .size(25.dp)
                                        .align(Alignment.Bottom),
                                ) {
                                    Icon(Icons.Filled.Add, "Add Item")
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(0.5f),
                            ) {
                                Row(
                                    modifier = Modifier.weight(0.5f),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    AnimatedContent(
                                        targetState = firiyaItem.count,
                                        transitionSpec = {
                                            if (targetState > oldCountItem) {
                                                addAnimationSlideVertical().using(
                                                    SizeTransform(clip = true),
                                                )
                                            } else {
                                                removeAnimationSlideVertical().using(
                                                    SizeTransform(clip = true),
                                                )
                                            }
                                        },
                                        label = "",
                                    ) { count ->

                                        Text(
                                            text = stringResource(
                                                id = R.string.price,
                                                (firiyaItem.price.toDouble() * count).formatPrice(),
                                            ),
                                            style = MaterialTheme.typography.headlineSmall,
                                            textAlign = TextAlign.Center, // Metni sağa hizalayın
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(top = 8.dp)
                                                .align(Alignment.Top),
                                            // Metni sağ alt köşeye hizalayın
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun BottomTotal(
    totalCount: MutableState<Double>,
    navigateToHome: (String, List<FiriyaUI>) -> Unit,
    firiyaList: List<FiriyaUI>,
    coroutineScope: CoroutineScope,

) {
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        verticalArrangement = Arrangement.SpaceBetween,

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFEEEEEE)),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(0.6f)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .weight(1f),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.5f),
                        ) {
                            Text(
                                text = "Total Price : ",
                                fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                                fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
                                fontSize = 16.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterStart),
                            )
                        }

                        var oldTotalCount by remember {
                            mutableStateOf(totalCount.value)
                        }
                        AnimatedContent(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.5f)
                                .align(Alignment.CenterVertically),
                            targetState = totalCount.value,
                            transitionSpec = {
                                if (targetState > oldTotalCount) {
                                    addAnimationSlideVertical().using(
                                        SizeTransform(clip = false),
                                    )
                                } else {
                                    removeAnimationSlideVertical().using(
                                        SizeTransform(clip = false),
                                    )
                                }
                            },
                            label = "ss",
                        ) { totalCount ->
                            Box(
                                modifier = Modifier.fillMaxSize(),

                            ) {
                                oldTotalCount = totalCount
                                Text(
                                    text = stringResource(
                                        id = R.string.price,
                                        totalCount.formatPrice(),
                                    ),
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.CenterStart),
                                    fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                                    fontSize = 17.sp,
                                    color = Color(0xFF6650a4),
                                )
                            }
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.4f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        isSheetOpen = true
                    },

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF6650a4)),

                    ) {
                        Text(
                            text = "Buy Now",
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
        BottomSheet(isSheetOpen, navigateToHome, firiyaList, coroutineScope)
    }
}

@ExperimentalMaterial3Api
@Composable
fun BottomSheet(
    isSheetOpen: Boolean,
    navigateToHome: (String, List<FiriyaUI>) -> Unit,
    firiyaList: List<FiriyaUI>,
    coroutineScope: CoroutineScope,

) {
    val sheetState = rememberModalBottomSheetState()
    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { /*TODO*/ },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        coroutineScope.launch { sheetState.hide() }
                        navigateToHome.invoke("Paid by card 1", firiyaList)
                    },
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF6650a4)),

                    ) {
                        Text(
                            text = "Paid by card 1",
                            fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            fontSize = 20.sp,
                            modifier = Modifier.align(alignment = Alignment.Center),

                            color = Color.White,
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.4f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        coroutineScope.launch { sheetState.hide() }
                        navigateToHome.invoke("Paid by card 2", firiyaList)
                    },
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF6650a4)),

                    ) {
                        Text(
                            text = "Paid by card 2",
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
