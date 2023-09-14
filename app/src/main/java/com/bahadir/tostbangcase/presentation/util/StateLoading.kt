package com.bahadir.tostbangcase.presentation.util

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

const val LOADING_ITEM_LAZY_COLUMN_TAG = "loading_item_lazy_column_test_tag"

@Composable
fun StateLoading() {
    val placeHolderList = Array(10) {
        0
    }.toMutableList()

    LazyColumn(
        modifier = Modifier.testTag(LOADING_ITEM_LAZY_COLUMN_TAG),
        userScrollEnabled = false,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(placeHolderList) {
            LoadingItem()
        }
    }
}

@Composable
fun LoadingItem() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 750
                0.7f at 400
            },
            repeatMode = RepeatMode.Reverse,
        ),
        label = "",
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .background(Color.LightGray.copy(alpha = alpha))
                    .align(Alignment.CenterVertically),
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.LightGray.copy(alpha = alpha)),
            )

            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .alignByBaseline()
                    .padding(8.dp),
            )
        }
    }
}
