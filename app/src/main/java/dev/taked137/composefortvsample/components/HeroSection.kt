package dev.taked137.composefortvsample.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Text

@Composable
fun HeroSection(
  featuredContent: FeaturedContent,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 48.dp, vertical = 32.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Text(
      text = "${featuredContent.genre} • ${featuredContent.year} • ${featuredContent.rating}",
      fontSize = 14.sp,
      fontWeight = FontWeight.Medium,
      color = Color.White.copy(alpha = 0.8f)
    )

    Text(
      text = featuredContent.title,
      fontSize = 36.sp,
      fontWeight = FontWeight.Bold,
      color = Color.White,
      modifier = Modifier.padding(bottom = 4.dp)
    )

    Text(
      text = featuredContent.subtitle,
      fontSize = 20.sp,
      fontWeight = FontWeight.Medium,
      color = Color.White.copy(alpha = 0.95f),
      modifier = Modifier.padding(bottom = 8.dp)
    )

    ContentRatingAndDuration(featuredContent.duration)

    Text(
      text = featuredContent.description,
      fontSize = 16.sp,
      color = Color.White.copy(alpha = 0.9f),
      modifier = Modifier.padding(vertical = 8.dp),
      maxLines = 3,
      overflow = TextOverflow.Ellipsis
    )

    PurchaseButton()
  }
}

@Composable
private fun ContentRatingAndDuration(
  duration: String,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = "⭐ 8.5",
      fontSize = 14.sp,
      color = Color.White.copy(alpha = 0.9f)
    )
    Text(
      text = "•",
      fontSize = 14.sp,
      color = Color.White.copy(alpha = 0.6f)
    )
    Text(
      text = duration,
      fontSize = 14.sp,
      color = Color.White.copy(alpha = 0.9f)
    )
  }
}

@Composable
private fun PurchaseButton(
  modifier: Modifier = Modifier,
) {
  Button(
    modifier = modifier,
    onClick = { /* do nothing */ },
    colors = ButtonDefaults.colors(
      containerColor = Color.Gray,
      contentColor = Color.White,
      focusedContainerColor = Color.White.copy(alpha = 0.9f),
      focusedContentColor = Color.Black
    ),
  ) {
    Text(
      text = "購入またはレンタル",
      fontSize = 16.sp,
      fontWeight = FontWeight.Medium
    )
  }
}

data class FeaturedContent(
  val title: String,
  val subtitle: String,
  val genre: String,
  val year: String,
  val rating: String,
  val duration: String,
  val description: String,
  val director: String,
  val cast: List<String>,
  val imageResId: Int,
)

@Preview(
  name = "tv",
  device = "spec:width=962dp,height=541dp,dpi=213",
  uiMode = Configuration.UI_MODE_TYPE_TELEVISION,
)
@Composable
private fun HeroSectionPreview() {
  HeroSection(
    featuredContent = FeaturedContent(
      title = "星翔の戦士",
      subtitle = "宇宙を舞台にした壮大なSFアクション",
      genre = "SF・アクション",
      year = "2025",
      rating = "PG-13",
      duration = "2時間15分",
      description = "遥か彼方の銀河で繰り広げられる正義と悪の戦い。若き戦士カイトが仲間たちと共に宇宙の平和を守るため、邪悪な帝国軍に立ち向かう感動のスペースオペラ。",
      director = "カントク・タロウ",
      cast = listOf(
        "ヒーロー・イチロウ",
        "ヒロイン・ハナコ",
        "アクション・ジロウ",
        "ビーム・サクラ"
      ),
      imageResId = 0
    ),
  )
}

