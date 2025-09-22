package dev.taked137.composefortvsample.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.Alignment
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.ui.graphics.graphicsLayer
import dev.taked137.composefortvsample.R
import dev.taked137.composefortvsample.components.FeaturedContent
import dev.taked137.composefortvsample.components.HeroSection
import dev.taked137.composefortvsample.components.CardRowSection
import dev.taked137.composefortvsample.components.CardData
import dev.taked137.composefortvsample.components.CardRowData
import dev.taked137.composefortvsample.utils.PositionFocusedItemInLazyLayout
import dev.taked137.composefortvsample.utils.scrollParentOnChildFocus

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollSampleScreen(
  modifier: Modifier = Modifier,
  listState: LazyListState = rememberLazyListState(),
) {
  val isBackGroundVisible by remember {
    derivedStateOf { listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset < 50 }
  }
  val backgroundAlpha by animateFloatAsState(
    targetValue = if (isBackGroundVisible) 1.0f else 0f,
    label = "backgroundAlpha"
  )

  Box(
    modifier = modifier.fillMaxSize(),
  ) {
    BackgroundWithGradient(
      imageResId = R.mipmap.ic_launcher,
      alpha = backgroundAlpha,
      modifier = Modifier.fillMaxSize()
    )

    // ⭐️[scroll] step1: Use BringIntoViewSpec to control the focused item position
    // ⚠️Note: When trying step2, remove this
    // PositionFocusedItemInLazyLayout(
    //   parentFraction = 0.65f,
    //   childFraction = 1f,
    // ) {
      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = PaddingValues(vertical = 32.dp),
        state = listState,
      ) {
        item {
          HeroSection(
            // ⭐️[scroll] step2: Use BringIntoViewModifierNode to display the whole hero section
            // modifier = Modifier.scrollParentOnChildFocus(),
            featuredContent = createFeaturedContent(),
          )
        }

        items(createSampleCardRows()) { row ->
          key(row.title) {
            PositionFocusedItemInLazyLayout(
              parentFraction = 0.3f,
              childFraction = 0f
            ) {
              CardRowSection(row)
            }
          }
        }
      }
    // }
  }
}

private fun createFeaturedContent(): FeaturedContent {
  return FeaturedContent(
    title = "星翔の戦士",
    subtitle = "宇宙を舞台にした壮大なSFアクション",
    genre = "SF・アクション",
    year = "2025",
    rating = "PG-13",
    duration = "2時間15分",
    description = "遥か彼方の銀河で繰り広げられる正義と悪の戦い。若き戦士カイトが仲間たちと共に宇宙の平和を守るため、邪悪な帝国軍に立ち向かう感動のスペースオペラ。",
    director = "カントク・タロウ",
    cast = listOf("ヒーロー・イチロウ", "ヒロイン・ハナコ", "アクション・ジロウ", "ビーム・サクラ"),
    imageResId = R.mipmap.ic_launcher
  )
}

private fun createSampleCardRows(): List<CardRowData> {
  return (0..10).map { rowIndex ->
    CardRowData(
      title = "Row Title $rowIndex",
      cards = (0..9).map { cardIndex ->
        CardData(
          title = "Title $cardIndex",
          description = "Description $cardIndex",
          imageResId = R.mipmap.ic_launcher
        )
      }
    )
  }
}

@Composable
private fun BackgroundWithGradient(
  imageResId: Int,
  alpha: Float,
  modifier: Modifier = Modifier
) {
  Box(modifier = modifier) {
    // Background image
    Image(
      painter = painterResource(id = imageResId),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .fillMaxSize()
        .align(Alignment.CenterEnd)
        .graphicsLayer {
          this.alpha = alpha
        }
    )

    // Gradient overlay
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.horizontalGradient(
            colors = listOf(
              Color.Black.copy(alpha = 0.9f * alpha),
              Color.Black.copy(alpha = 0.7f * alpha),
              Color.Transparent
            ),
            startX = 0f,
            endX = 1200f
          )
        )
    )
  }
}

@Preview(
  name = "tv",
  device = "spec:width=962dp,height=541dp,dpi=213",
  uiMode = Configuration.UI_MODE_TYPE_TELEVISION,
)
@Composable
fun ScrollSampleScreenPreview() {
  ScrollSampleScreen()
}

