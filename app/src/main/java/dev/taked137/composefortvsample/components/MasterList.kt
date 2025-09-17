package dev.taked137.composefortvsample.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

@Composable
internal fun MasterList(
  onItemFocused: (Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  val itemRange = 0..9

  Column(
    modifier = modifier.verticalScroll(rememberScrollState()),
  ) {
    for (i in itemRange) {
      Item(
        text = "Category ${i + 1}",
        onFocusChanged = { isFocused ->
          if (isFocused) {
            onItemFocused(i)
          }
        }
      )

      if (i != itemRange.last) {
        HorizontalDivider()
      }
    }
  }
}

@Composable
private fun Item(
  text: String,
  onFocusChanged: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
  val isFocused by interactionSource.collectIsFocusedAsState()

  Surface(
    modifier = modifier
      .height(60.dp)
      .fillMaxWidth()
      .onFocusChanged { onFocusChanged(it.isFocused) },
    colors = ClickableSurfaceDefaults.colors(
      containerColor = if (isFocused) Color.Red.copy(alpha = 0.15f) else Color.White,
      contentColor = Color.Black,
      focusedContainerColor = Color.Red.copy(alpha = 0.3f),
      focusedContentColor = Color.Black,
    ),
    shape = ClickableSurfaceDefaults.shape(
      shape = RectangleShape,
      focusedShape = RectangleShape
    ),
    scale = ClickableSurfaceDefaults.scale(focusedScale = 1f),
    onClick = { /* do nothing */ },
  ) {
    Text(
      modifier = Modifier.align(Alignment.Center),
      text = text
    )
  }
}

@Composable
private fun HorizontalDivider(modifier: Modifier = Modifier) {
  Canvas(
    modifier = modifier
      .fillMaxWidth()
      .height(1.dp)
  ) {
    drawLine(
      color = Color.Black,
      strokeWidth = 1.dp.toPx(),
      start = Offset(0f, 1.dp.toPx() / 2),
      end = Offset(size.width, 1.dp.toPx() / 2),
    )
  }
}

@Preview
@Composable
private fun MasterListPreview() {
  val focusRequester = remember { FocusRequester() }

  MasterList(
    modifier = Modifier
      .width(200.dp)
      .background(Color.White)
      .focusRequester(focusRequester),
    onItemFocused = {},
  )

  LaunchedEffect(Unit) {
    focusRequester.requestFocus()
  }
}
