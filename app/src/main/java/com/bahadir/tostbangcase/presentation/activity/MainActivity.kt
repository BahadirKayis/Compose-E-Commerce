package com.bahadir.tostbangcase.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SignalWifiStatusbarConnectedNoInternet4
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.bahadir.tostbangcase.presentation.activity.state.ActivityUIState
import com.bahadir.tostbangcase.presentation.navigation.SetUpNavGraph
import com.bahadir.tostbangcase.presentation.theme.FiriyaTheme
import com.bahadir.tostbangcase.presentation.util.animation.AnimationPulsating
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            FiriyaApp()
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun FiriyaApp(
    viewModel: ActivityVM = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState(ActivityUIState())
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val isLight = MaterialTheme.colors.isLight

    SetUpNavGraph(navController = navController)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFF6650a4),
            darkIcons = !isLight,

            )
        systemUiController.isNavigationBarVisible = true
    }
    FiriyaTheme {
        NetworkState(state = state)
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun NetworkState(state: ActivityUIState) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .height(25.dp)
        ) {
            if (!state.networkStatus) {
                AnimationPulsating {
                    Surface(
                        modifier = Modifier
                            .size(25.dp)
                            .background(Color.White),
                        content = {
                            Icon(
                                imageVector = Icons.Outlined.SignalWifiStatusbarConnectedNoInternet4,
                                contentDescription = null,
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(25.dp)
                                    .align(Alignment.CenterVertically),

                                )
                        },
                    )
                }
            }
        }
    }
}
