package com.bahadir.tostbangcase.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Badge
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.presentation.activity.NavigationItem

@Composable
fun MainScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val items = listOf(
        NavigationItem(
            "Shopping History",
            ScreenRoute.ShoppingHistory.route,
            ImageVector.vectorResource(id = R.drawable.ic_history),
            ImageVector.vectorResource(id = R.drawable.ic_history),
            false,
        ),
        NavigationItem(
            "Home",
            ScreenRoute.Home.route,
            Icons.Filled.Home,
            Icons.Outlined.Home,
            true,
        ),
        NavigationItem(
            "Basket",
            ScreenRoute.Basket.route,
            Icons.Filled.ShoppingCart,
            Icons.Outlined.ShoppingCart,
            false,
            badgeCount = 10,
        ),
    )

    Navigation(screens = items, navController = navController, modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    screens: List<NavigationItem>,
    navController: NavHostController,
    modifier: Modifier,
) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colors.surface),

    ) {
        screens.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route == item.route
                } == true,

                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    BadgedBox(badge = {
                        if (item.badgeCount != null) {
                            Badge {
                                Text(text = item.badgeCount.toString())
                            }
                        } else if (item.hasNews) {
                            Badge {}
                        }
                    }) {
                        Icon(
                            imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unSelectedIcon,
                            contentDescription = item.title,
                        )
                    }
                },
            )
        }
    }
}
