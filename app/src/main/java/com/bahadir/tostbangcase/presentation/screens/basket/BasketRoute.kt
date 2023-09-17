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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.core.extensions.formatPrice
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.presentation.screens.basket.state.BasketUIEvent
import com.bahadir.tostbangcase.presentation.screens.basket.state.BasketUIState
import com.bahadir.tostbangcase.presentation.screens.basket.state.ProductState
import com.bahadir.tostbangcase.presentation.util.animation.addAnimationSlideHorizontal
import com.bahadir.tostbangcase.presentation.util.animation.addAnimationSlideVertical
import com.bahadir.tostbangcase.presentation.util.animation.removeAnimationSlideHorizontal
import com.bahadir.tostbangcase.presentation.util.animation.removeAnimationSlideVertical
import com.bahadir.tostbangcase.presentation.util.basecomponent.BottomNavigationScaffold
import com.bahadir.tostbangcase.presentation.util.basecomponent.BottomTotal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun BasketRoute(
    navController: NavHostController,
    viewModel: BasketVM = hiltViewModel(),
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.state.collectAsState(initial = BasketUIState())

    BasketScreen(
        state = uiState,
        navController = navController,
        viewModel = viewModel,
        navigateToHome = navigateToHome,
    )
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun BasketScreen(
    state: BasketUIState,
    viewModel: BasketVM,
    navController: NavHostController,
    navigateToHome: () -> Unit,
) {
    val totalCount = remember { mutableDoubleStateOf(0.0) }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val isSheetDialogOpen = rememberSaveable {
        mutableStateOf(false)
    }

    BottomNavigationScaffold(navController, scaffoldState) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f),
            ) {
                state.uiData?.let {
                    if (it.isEmpty())
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.empty_history),
                                    contentDescription = "null",
                                    tint = Color(0xFFB39DDB),
                                    modifier = Modifier
                                        .size(150.dp)
                                )

                            }
                        }
                    else BasketProductList(
                        productList = it,
                        totalCount = totalCount,
                        viewModel = viewModel,
                    )


                } ?: Text(
                    text = "Basket is empty",
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f),
                verticalAlignment = Alignment.Bottom,
            ) {
                BottomTotal(totalCount.doubleValue, R.string.total_price, R.string.pay) {
                    isSheetDialogOpen.value = true
                }
            }
        }
    }

    state.navigateToHomeCardName?.let { cardName ->
        coroutineScope.launch {
            val job = coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "$cardName payment successful",
                    duration = SnackbarDuration.Indefinite,
                )
            }
            delay(1000)
            navigateToHome.invoke()
            job.cancel()
        }
    }

    BottomSheet(
        isSheetDialog = isSheetDialogOpen,
        viewModel = viewModel,
        coroutineScope = coroutineScope,
    )
}

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun BasketProductList(
    productList: List<FiriyaUI>,
    totalCount: MutableState<Double>,
    viewModel: BasketVM,
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),

    ) {
        items(productList) {
            ProductItem(it, totalCount, viewModel)
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun ProductItem(product: FiriyaUI, totalCount: MutableState<Double>, viewModel: BasketVM) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    val height by animateDpAsState(
        if (isExpanded) 175.dp else 100.dp,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing), label = "",
    )
    var oldCountItem by remember { mutableIntStateOf(product.count) }

    LaunchedEffect(product.count) {
        var diff = product.count - oldCountItem
        if (diff == 0) diff = product.count
        totalCount.value += (diff * product.price.toDouble())
        oldCountItem = product.count
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
                model = product.image,
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
                        text = product.title,
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
                            ProductControl(
                                viewModel = viewModel,
                                product = product,
                                modifier = Modifier.weight(0.5f),
                                oldCountItem = oldCountItem,
                            )

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
                                        targetState = product.count,
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
                                                id = R.string.price_type,
                                                (product.price.toDouble() * count).formatPrice(),
                                            ),
                                            style = MaterialTheme.typography.headlineSmall,
                                            textAlign = TextAlign.Center,
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(top = 8.dp)
                                                .align(Alignment.Top),

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
@Composable
fun ProductControl(viewModel: BasketVM, product: FiriyaUI, modifier: Modifier, oldCountItem: Int) {
    Row(
        modifier = modifier,

        ) {
        SmallFloatingActionButton(
            onClick = {
                viewModel.setEvent(
                    BasketUIEvent.UpdateProduct(
                        product,
                        ProductState.REMOVE,
                    ),
                )
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
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth()
                .align(Alignment.Bottom),
        ) {
            AnimatedContent(
                targetState = product.count,
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
                        .fillMaxWidth(),

                    )
            }
        }

        SmallFloatingActionButton(
            onClick = {
                viewModel.setEvent(
                    BasketUIEvent.UpdateProduct(
                        product,
                        ProductState.ADD,
                    ),
                )
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
}

@ExperimentalMaterial3Api
@Composable
fun BottomSheet(
    isSheetDialog: MutableState<Boolean>,
    viewModel: BasketVM,
    coroutineScope: CoroutineScope,

    ) {
    val sheetState = rememberModalBottomSheetState()
    if (isSheetDialog.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { isSheetDialog.value = false },

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
                        viewModel.setEvent(
                            BasketUIEvent.BottomSheetBasketSold(
                                "Paid by card 1",

                                ),
                        )
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
                        viewModel.setEvent(
                            BasketUIEvent.BottomSheetBasketSold(
                                "Paid by card 2",

                                ),
                        )
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
