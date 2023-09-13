package com.bahadir.tostbangcase.presentation.navigation

sealed class ScreenNav(val route: String) {
    data object Login : ScreenNav("LoginRoute")
    data object Home : ScreenNav("HomeRoute")
    data object Detail : ScreenNav("DetailRoute")
    data object Basket : ScreenNav("BasketRoute")
    data object ShoppingHistory : ScreenNav("ShoppingHistoryRoute")
}
