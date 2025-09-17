package dev.taked137.composefortvsample.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.taked137.composefortvsample.components.DetailList
import dev.taked137.composefortvsample.components.MasterList
import dev.taked137.composefortvsample.ui.theme.ComposeForTVSampleTheme

@Composable
internal fun FocusSampleScreen(
  modifier: Modifier = Modifier,
) {
  val (focusedMasterListIndex, setIndex) = remember { mutableIntStateOf(0) }

  Row(
    modifier = modifier,
  ) {
    MasterList(
      modifier = Modifier.width(200.dp),
      onItemFocused = setIndex,
    )
    DetailList(
      modifier = Modifier.weight(1f),
      listIndex = focusedMasterListIndex,
    )
  }
}

@Preview(
  name = "tv",
  showBackground = true,
  device = "spec:width=962dp,height=541dp,dpi=213",
  uiMode = Configuration.UI_MODE_TYPE_TELEVISION,
)
@Composable
private fun FocusSampleScreenPreview() {
  val focusRequester = remember { FocusRequester() }

  ComposeForTVSampleTheme {
    FocusSampleScreen(
      modifier = Modifier.focusRequester(focusRequester),
    )
  }

  LaunchedEffect(Unit) {
    focusRequester.requestFocus()
  }
}
