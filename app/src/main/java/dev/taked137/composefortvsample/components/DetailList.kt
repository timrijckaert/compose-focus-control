package dev.taked137.composefortvsample.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import dev.taked137.composefortvsample.R

@Composable
internal fun DetailList(
  listIndex: Int,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier
      .background(Color.Gray.copy(alpha = 0.1f))
      .fillMaxSize(),
    state = rememberLazyListState(arrayOf(listIndex)),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    items(10) {
      Item(
        modifier = Modifier.height(100.dp),
        listIndex = listIndex,
        itemIndex = it,
      )
    }
  }
}

@Composable
private fun Item(
  listIndex: Int,
  itemIndex: Int,
  modifier: Modifier = Modifier,
) {
  Surface(
    modifier = modifier,
    colors = ClickableSurfaceDefaults.colors(
      containerColor = Color.White,
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
    Row(
      modifier = Modifier.align(Alignment.Center),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      Image(
        painter = painterResource(imageRes[listIndex % imageRes.size]),
        contentDescription = null,
      )
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Detail Item ${itemIndex + 1}\n(Category ${listIndex + 1})",
        fontSize = 20.sp,
      )
    }
  }
}

@Composable
internal fun rememberLazyListState(
  keys: Array<Any>,
  initialFirstVisibleItemIndex: Int = 0,
  initialFirstVisibleItemScrollOffset: Int = 0,
): LazyListState {
  return rememberSaveable(inputs = keys, saver = LazyListState.Saver) {
    LazyListState(
      initialFirstVisibleItemIndex,
      initialFirstVisibleItemScrollOffset
    )
  }
}

private val imageRes = listOf(
  R.drawable.img0,
  R.drawable.img1,
  R.drawable.img2,
  R.drawable.img3,
  R.drawable.img4,
  R.drawable.img5,
  R.drawable.img6,
)

@Preview(
  name = "tv",
  showBackground = true,
  device = "spec:width=962dp,height=541dp,dpi=213",
  uiMode = Configuration.UI_MODE_TYPE_TELEVISION,
)
@Composable
private fun DetailListPreview() {
  val focusRequester = remember { FocusRequester() }

  DetailList(
    listIndex = 0,
    modifier = Modifier
      .fillMaxWidth()
      .focusRequester(focusRequester),
  )

  LaunchedEffect(Unit) {
    focusRequester.requestFocus()
  }
}
