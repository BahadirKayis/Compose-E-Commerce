package com.bahadir.tostbangcase.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bahadir.tostbangcase.core.Constants.PRODUCT
import com.bahadir.tostbangcase.presentation.screens.basket.BasketRoute
import com.bahadir.tostbangcase.presentation.screens.detail.DetailRoute
import com.bahadir.tostbangcase.presentation.screens.home.HomeRoute
import com.bahadir.tostbangcase.presentation.screens.login.LoginScreen
import com.bahadir.tostbangcase.presentation.screens.shoppinghistory.ShoppingHistoryRoute

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        addLogin(navController = navController)
        addHome(navController = navController)
        addBasket(navController = navController)
        addShoppingHistory(navController = navController)
        addDetail(navController = navController)
    }
}

fun NavGraphBuilder.addLogin(navController: NavHostController) {
    composable(route = ScreenRoute.Login.route) {
        LoginScreen(navigeteToHome = {
            navController.navigate(route = ScreenRoute.Home.route)
        })
    }
}

fun NavGraphBuilder.addDetail(navController: NavHostController) {
    composable(
        route = "${ScreenRoute.Detail.route}/{$PRODUCT}",
        arguments = listOf(
            navArgument(PRODUCT) {
                type = NavType.StringType
            },
        ),
    ) {
        DetailRoute()
    }
}

fun NavGraphBuilder.addHome(navController: NavHostController) {
    composable(route = ScreenRoute.Home.route) {
        HomeRoute(navController, onProductClicked = { firiyaItem ->
            val route = "${ScreenRoute.Detail.route}/{${firiyaItem.id}}"
            navController.navigate(route = route)
        })
    }
}

fun NavGraphBuilder.addBasket(navController: NavHostController) {
    composable(route = ScreenRoute.Basket.route) {
        BasketRoute {
            val route = "${ScreenRoute.Detail.route}/$it"
            navController.navigate(route = route)
        }
    }
}

fun NavGraphBuilder.addShoppingHistory(navController: NavHostController) {
    composable(route = ScreenRoute.ShoppingHistory.route) {
        ShoppingHistoryRoute()
    }
}
