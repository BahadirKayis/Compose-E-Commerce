package com.bahadir.tostbangcase.presentation.util

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith

@ExperimentalAnimationApi
fun addAnimationSlideVertical(duration: Int = 800): ContentTransform {
    return (slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration),
    )).togetherWith(slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
        animationSpec = tween(durationMillis = duration),
    ))
}

@ExperimentalAnimationApi
fun removeAnimationSlideVertical(duration: Int = 800): ContentTransform {
    return (slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeIn(
        animationSpec = tween(durationMillis = duration),
    )).togetherWith(slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeOut(
        animationSpec = tween(durationMillis = duration),
    ))
}

@ExperimentalAnimationApi
fun addAnimationSlideHorizontal(duration: Int = 800): ContentTransform {
    return (slideInHorizontally(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration),
    )).togetherWith(slideOutHorizontally(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
        animationSpec = tween(durationMillis = duration),
    ))
}

@ExperimentalAnimationApi
fun removeAnimationSlideHorizontal(duration: Int = 800): ContentTransform {
    return (slideInHorizontally(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeIn(
        animationSpec = tween(durationMillis = duration),
    )).togetherWith(slideOutHorizontally(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeOut(
        animationSpec = tween(durationMillis = duration),
    ))
}
