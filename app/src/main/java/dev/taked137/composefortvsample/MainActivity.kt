package dev.taked137.composefortvsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.taked137.composefortvsample.navigation.Screen
import dev.taked137.composefortvsample.screens.FocusSampleScreen
import dev.taked137.composefortvsample.screens.HomeScreen
import dev.taked137.composefortvsample.ui.theme.ComposeForTVSampleTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ComposeForTVSampleTheme {
        AppNavigation(
          modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        )
      }
    }
  }
}

@Composable
private fun AppNavigation(
  modifier: Modifier = Modifier,
) {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = Screen.HOME.route,
    modifier = modifier,
  ) {
    composable(Screen.HOME.route) {
      HomeScreen(
        onNavigateToFocusSample = {
          navController.navigate(Screen.FOCUS_SAMPLE.route)
        }
      )
    }

    composable(Screen.FOCUS_SAMPLE.route) {
      FocusSampleScreen()
    }
  }
}
