package com.bahadir.tostbangcase.presentation.util.basecomponent

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.core.extensions.formatPrice
import com.bahadir.tostbangcase.presentation.navigation.MainScreen
import com.bahadir.tostbangcase.presentation.util.animation.addAnimationSlideVertical
import com.bahadir.tostbangcase.presentation.util.animation.removeAnimationSlideVertical
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun BottomNavigationScaffold(
    navController: NavHostController,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MainScreen(navController = navController, Modifier.navigationBarsPadding()) },
    ) { paddingValue ->
        Surface(
            color = Color(0xffededed),
            modifier = Modifier.padding(bottom = paddingValue.calculateBottomPadding()),
        ) {
            content()
        }
    }
}

@Composable
fun BottomNavigationScaffold(
    navController: NavHostController,
    state: ScaffoldState,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = state,
        bottomBar = { MainScreen(navController = navController, Modifier.navigationBarsPadding()) },
    ) { paddingValue ->
        Surface(
            color = Color(0xffededed),
            modifier = Modifier.padding(bottom = paddingValue.calculateBottomPadding()),
        ) {
            content()
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun BottomTotal(
    totalCount: Double,
    @StringRes priceName: Int,
    @StringRes buttonName: Int,
    bottomSheet: () -> Unit,
) {
    var oldTotalCount by remember {
        mutableDoubleStateOf(totalCount)
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
                                text = stringResource(id = priceName),

                                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                                fontSize = 17.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterStart),
                            )
                        }

                        AnimatedContent(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.5f)
                                .align(Alignment.CenterVertically),
                            targetState = totalCount,
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
                                        id = R.string.price_type,
                                        totalCount.formatPrice(),
                                    ),
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.CenterStart),
                                    fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                                    fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
                                    fontSize = 18.sp,
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
                        bottomSheet.invoke()
                    },

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF6650a4)),

                    ) {
                        Text(
                            text = stringResource(id = buttonName),
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
