package dev.taked137.composefortvsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.taked137.composefortvsample.screens.FocusSampleScreen
import dev.taked137.composefortvsample.ui.theme.ComposeForTVSampleTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ComposeForTVSampleTheme {
        FocusSampleScreen(
          modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        )
      }
    }
  }
}
