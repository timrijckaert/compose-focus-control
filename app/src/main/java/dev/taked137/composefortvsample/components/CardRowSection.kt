package dev.taked137.composefortvsample.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LocalPinnableContainer
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.Text
import dev.taked137.composefortvsample.R

@Composable
fun CardRowSection(
  row: CardRowData,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier,
  ) {
    Text(
      text = row.title,
      fontSize = 20.sp,
      fontWeight = FontWeight.Medium,
      color = Color.White,
      modifier = Modifier.padding(bottom = 8.dp, start = 48.dp)
    )

    val pinnableContainer = LocalPinnableContainer.current
    DisposableEffect(pinnableContainer) {
      val handle = pinnableContainer?.pin()
      onDispose { handle?.release() }
    }

    val focusRequester = remember { FocusRequester() }
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .focusRequester(focusRequester)
        .focusRestorer(focusRequester),
      contentPadding = PaddingValues(horizontal = 48.dp),
    ) {
      items(row.cards) { card ->
        CardItem(card = card)
      }
    }
  }
}

@Composable
fun CardItem(card: CardData) {
  val cardWidth = 90.dp
  val imageHeight = 50.dp
  Card(
    colors = CardDefaults.colors(
      containerColor = Color.Transparent,
      focusedContainerColor = Color.Red.copy(alpha = 0.3f),
    ),
    scale = CardDefaults.scale(
      scale = 1f,
      focusedScale = 1f,
    ),
    border = CardDefaults.border(
      border = Border.None,
      focusedBorder = Border(BorderStroke(2.dp, Color.White)),
    ),
    onClick = { /* do nothing */ },
    modifier = Modifier.background(Color.Black.copy(alpha = 0.15f)),
  ) {
    Column(
      modifier = Modifier.padding(8.dp)
    ) {
      Image(
        painter = painterResource(id = R.mipmap.ic_launcher),
        contentDescription = card.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .width(cardWidth)
          .height(imageHeight)
      )

      Text(
        text = card.title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier
          .width(cardWidth)
          .padding(top = 4.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )

      Text(
        text = card.description,
        fontSize = 12.sp,
        color = Color.White.copy(alpha = 0.8f),
        modifier = Modifier.width(cardWidth),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}

data class CardData(
  val title: String,
  val description: String,
  val imageResId: Int,
)

data class CardRowData(
  val title: String,
  val cards: List<CardData>,
)

@Preview(
  name = "tv",
  device = "spec:width=962dp,height=541dp,dpi=213",
  uiMode = Configuration.UI_MODE_TYPE_TELEVISION,
)
@Composable
private fun CardRowSectionPreview() {
  CardRowSection(
    row = CardRowData(
      title = "おすすめの映画",
      cards = List(10) {
        CardData(
          title = "Card Title $it",
          description = "This is a description for card number $it.",
          imageResId = R.mipmap.ic_launcher,
        )
      }
    ),
  )
}
