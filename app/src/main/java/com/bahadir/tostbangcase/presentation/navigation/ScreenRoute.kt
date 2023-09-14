package com.bahadir.tostbangcase.presentation.navigation

sealed class ScreenRoute(val route: String) {
    data object Login : ScreenRoute("LoginRoute")
    data object Home : ScreenRoute("HomeRoute")
    data object Detail : ScreenRoute("DetailRoute")
    data object Basket : ScreenRoute("BasketRoute")
    data object ShoppingHistory : ScreenRoute("ShoppingHistoryRoute")
}
