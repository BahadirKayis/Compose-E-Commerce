package com.bahadir.tostbangcase.presentation.navigation

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bahadir.tostbangcase.core.Constants
import com.bahadir.tostbangcase.presentation.screens.basket.BasketRoute
import com.bahadir.tostbangcase.presentation.screens.detail.DetailRoute
import com.bahadir.tostbangcase.presentation.screens.home.HomeRoute
import com.bahadir.tostbangcase.presentation.screens.login.LoginScreen
import com.bahadir.tostbangcase.presentation.screens.shoppinghistory.ShoppingHistoryRoute
import com.google.gson.Gson

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenRoute.Login.route) {
        addLogin(navController = navController)
        addHome(navController = navController)
        addBasket(navController = navController)
        addShoppingHistory(navController = navController)
        addDetail()
    }
}

fun NavGraphBuilder.addLogin(navController: NavHostController) {
    composable(route = ScreenRoute.Login.route) {
        LoginScreen(navigateToHome = {
            navController.navigate(route = ScreenRoute.Home.route)
        })
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addDetail() {
    composable(
        route = "${ScreenRoute.Detail.route}/{${Constants.DETAIL}}",
        arguments = listOf(
            navArgument(Constants.DETAIL) {
                type = AssetParamType()
            },
        ),
    ) {
        DetailRoute()
    }
}

fun NavGraphBuilder.addHome(navController: NavHostController) {
    composable(route = ScreenRoute.Home.route) {
        HomeRoute(navController, onProductClicked = { firiyaItem ->
            val json = Uri.encode(Gson().toJson(firiyaItem))
            val route = "${ScreenRoute.Detail.route}/$json"
            navController.navigate(route = route)
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
fun NavGraphBuilder.addBasket(navController: NavHostController) {
    composable(route = ScreenRoute.Basket.route) {
        BasketRoute(
            navController = navController,
            navigateToHome = {
                navController.popBackStack()
            },
        )
    }
}

@ExperimentalMaterial3Api
@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.addShoppingHistory(navController: NavHostController) {
    composable(route = ScreenRoute.ShoppingHistory.route) {
        ShoppingHistoryRoute(navController, routeLogin = {
            navController.navigate(route = ScreenRoute.Login.route)
        })
    }
}
