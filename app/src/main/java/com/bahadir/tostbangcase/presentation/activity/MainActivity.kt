package com.bahadir.tostbangcase.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.VectorConverter
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.bahadir.tostbangcase.presentation.navigation.SetUpNavGraph
import com.bahadir.tostbangcase.presentation.theme.TOSTBANGCaseTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

data class NavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasNews: Boolean,
    var badgeCount: Int? = null,
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiriyaApp(name = "Firiya")
        }
    }
}

@Composable
fun FiriyaApp(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val isLight = MaterialTheme.colors.isLight
    SetUpNavGraph(navController = navController)
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Blue,
            darkIcons = isLight,

        )
        systemUiController.isNavigationBarVisible = false
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TOSTBANGCaseTheme {
    }
}
