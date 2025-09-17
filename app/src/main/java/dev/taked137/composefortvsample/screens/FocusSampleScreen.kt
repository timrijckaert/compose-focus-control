package dev.taked137.composefortvsample.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
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
  val focusRequester = remember { FocusRequester() }

  Row(
    modifier = modifier,
  ) {
    MasterList(
      modifier = Modifier
        .width(200.dp)
        // ⭐️[focus] step1: Enable focusRestorer to remember and restore focus position
        // .focusRestorer() // applied to Column, but verticalScroll is also applied internally, so focusGroup is not needed
        ,
      onItemFocused = setIndex,
    )
    // ⭐️[focus] step2: Use `key` to reset focusRestorer state when the ui changes
    // key(focusedMasterListIndex) {
      DetailList(
        modifier = Modifier
          .weight(1f)
          // ⭐️[focus] step1: Enable focusRestorer to remember and restore focus position
          // .focusRestorer() // applied to LazyColumn, so focusGroup is not needed
          // ⚠️Note: When using step3, remove step1's .focusRestorer() above

          // ⭐️[focus] step3: Use FocusRequester as a fallback target for focusRestorer
          // .focusRequester(focusRequester)
          // .focusRestorer(focusRequester) // applied to LazyColumn, so focusGroup is not needed
        ,
        listIndex = focusedMasterListIndex,
      )
    // }
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
