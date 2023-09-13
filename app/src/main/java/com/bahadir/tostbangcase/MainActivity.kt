package com.bahadir.tostbangcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Badge
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bahadir.tostbangcase.presentation.navigation.ScreenNav
import com.bahadir.tostbangcase.presentation.navigation.SetUpNavGraph
import com.bahadir.tostbangcase.presentation.theme.TOSTBANGCaseTheme
import dagger.hilt.android.AndroidEntryPoint

data class NavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasNews: Boolean,
    var badgeCount: Int? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TOSTBANGCaseTheme {
                var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
                navController = rememberNavController()
                SetUpNavGraph(navController = navController)

                val items = listOf(
                    NavigationItem(
                        "Shopping History",
                        ScreenNav.ShoppingHistory.route,
                        ImageVector.vectorResource(id = R.drawable.ic_history),
                        ImageVector.vectorResource(id = R.drawable.ic_history),
                        false,
                    ),
                    NavigationItem(
                        "Home",
                        ScreenNav.Home.route,
                        Icons.Filled.Home,
                        Outlined.Home,
                        true,
                    ),
                    NavigationItem(
                        "Basket",
                        ScreenNav.Basket.route,
                        Icons.Filled.ShoppingCart,
                        Outlined.ShoppingCart,
                        false,
                        badgeCount = 10,
                    ),

                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Scaffold(bottomBar = {
                        NavigationBar {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        navController.navigate(item.route)
                                    },
                                    label = {
                                        Text(text = item.title)
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
                    }) {
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TOSTBANGCaseTheme {
            Greeting("Android")
        }
    }
}
