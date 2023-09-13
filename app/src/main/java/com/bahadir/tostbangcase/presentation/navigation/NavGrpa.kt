package com.bahadir.tostbangcase.presentation.navigation

import android.os.Build.PRODUCT
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bahadir.tostbangcase.presentation.screens.basket.BasketRoute
import com.bahadir.tostbangcase.presentation.screens.detail.DetailRoute
import com.bahadir.tostbangcase.presentation.screens.home.HomeRoute
import com.bahadir.tostbangcase.presentation.screens.login.LoginScreen
import com.bahadir.tostbangcase.presentation.screens.shoppinghistory.ShoppingHistoryRoute

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenNav.Home.route) {
        addLogin(navController = navController)
        addHome(navController = navController)
        addBasket(navController = navController)
        addShoppingHistory(navController = navController)
        addDetail(navController = navController)
    }
}

fun NavGraphBuilder.addLogin(navController: NavHostController) {
    composable(route = ScreenNav.Login.route) {
        Log.e("TAG", "addLogin: ")
        LoginScreen(navigeteToHome = {
            navController.navigate(route = ScreenNav.Home.route)
        })
    }
}

fun NavGraphBuilder.addDetail(navController: NavHostController) {
    composable(
        route = ScreenNav.Detail.route,
        arguments = listOf(
            navArgument(PRODUCT) {
                type = NavType.StringType
            },
        ),
    ) {
        Log.e("TAG", "addDetail: ")
        DetailRoute()
    }
}

fun NavGraphBuilder.addHome(navController: NavHostController) {
    composable(route = ScreenNav.Home.route) {
        Log.e("TAG", "addHome: ")
        HomeRoute()
    }
}

fun NavGraphBuilder.addBasket(navController: NavHostController) {
    composable(route = ScreenNav.Basket.route) {
        BasketRoute {
            Log.e("TAG", "addBasket: ")
            val route = "${ScreenNav.Detail.route}/$it"
            navController.navigate(route = route)
        }
    }
}

fun NavGraphBuilder.addShoppingHistory(navController: NavHostController) {
    composable(route = ScreenNav.ShoppingHistory.route) {
        Log.e("TAG", "addShoppingHistory: ")
        ShoppingHistoryRoute()
    }
}
