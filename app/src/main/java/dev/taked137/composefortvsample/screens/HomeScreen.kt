package dev.taked137.composefortvsample.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Text
import dev.taked137.composefortvsample.ui.theme.ComposeForTVSampleTheme

@Composable
internal fun HomeScreen(
  onNavigateToFocusSample: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
      .background(Color.White),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Text(
      text = "Compose for TV Sample",
      modifier = Modifier.padding(bottom = 24.dp)
    )

    Button(
      colors = ButtonDefaults.colors(
        focusedContainerColor = Color.Red.copy(alpha = 0.3f),
      ),
      border = ButtonDefaults.border(
        focusedBorder = Border(
          border = BorderStroke(2.dp, Color.Red.copy(alpha = 0.3f)),
          inset = 4.dp,
        ),
      ),
      onClick = onNavigateToFocusSample,
    ) {
      Text("Focus Sample Screen")
    }
  }
}

@Preview(
  name = "tv",
  showBackground = true,
  device = "spec:width=962dp,height=541dp,dpi=213",
  uiMode = Configuration.UI_MODE_TYPE_TELEVISION,
)
@Composable
private fun HomeScreenPreview() {
  ComposeForTVSampleTheme {
    HomeScreen(
      onNavigateToFocusSample = {},
    )
  }
}
