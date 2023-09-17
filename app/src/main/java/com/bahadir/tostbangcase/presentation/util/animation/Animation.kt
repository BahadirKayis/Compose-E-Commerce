package com.bahadir.tostbangcase.presentation.util.animation

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale

@ExperimentalAnimationApi
fun addAnimationSlideVertical(duration: Int = 800): ContentTransform {
    return (
        slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
            animationSpec = tween(durationMillis = duration),
        )
        ).togetherWith(
        slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
            animationSpec = tween(durationMillis = duration),
        ),
    )
}

@ExperimentalAnimationApi
fun removeAnimationSlideVertical(duration: Int = 800): ContentTransform {
    return (
        slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeIn(
            animationSpec = tween(durationMillis = duration),
        )
        ).togetherWith(
        slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeOut(
            animationSpec = tween(durationMillis = duration),
        ),
    )
}

@ExperimentalAnimationApi
fun addAnimationSlideHorizontal(duration: Int = 800): ContentTransform {
    return (
        slideInHorizontally(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
            animationSpec = tween(durationMillis = duration),
        )
        ).togetherWith(
        slideOutHorizontally(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
            animationSpec = tween(durationMillis = duration),
        ),
    )
}

@ExperimentalAnimationApi
fun removeAnimationSlideHorizontal(duration: Int = 800): ContentTransform {
    return (
        slideInHorizontally(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeIn(
            animationSpec = tween(durationMillis = duration),
        )
        ).togetherWith(
        slideOutHorizontally(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeOut(
            animationSpec = tween(durationMillis = duration),
        ),
    )
}

@Composable
fun AnimationPulsating(pulseFraction: Float = 1.2f, content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "",
    )

    Box(modifier = Modifier.scale(scale)) {
        content()
    }
}
