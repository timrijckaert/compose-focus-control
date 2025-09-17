package dev.taked137.composefortvsample.utils

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.BringIntoViewSpec
import androidx.compose.foundation.gestures.LocalBringIntoViewSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PositionFocusedItemInLazyLayout(
  parentFraction: Float = 0.3f,
  childFraction: Float = 0f,
  content: @Composable () -> Unit,
) {
  val bringIntoViewSpec = remember(parentFraction, childFraction) {
    createBringIntoViewSpec(parentFraction, childFraction)
  }

  CompositionLocalProvider(
    LocalBringIntoViewSpec provides bringIntoViewSpec,
    content = content,
  )
}

private fun createBringIntoViewSpec(
  parentFraction: Float,
  childFraction: Float,
  isScrollEnabled: Boolean = true,
): BringIntoViewSpec {
  return object : BringIntoViewSpec {

    override val scrollAnimationSpec: AnimationSpec<Float> = tween(
      durationMillis = 125,
      easing = CubicBezierEasing(0.25f, 0.1f, .25f, 1f),
    )

    override fun calculateScrollDistance(offset: Float, size: Float, containerSize: Float): Float {
      if (!isScrollEnabled) return 0f

      val childSmallerThanParent = size <= containerSize
      val initialTargetForLeadingEdge = parentFraction * containerSize - (childFraction * size)
      val spaceAvailableToShowItem = containerSize - initialTargetForLeadingEdge

      val targetForLeadingEdge = if (childSmallerThanParent && spaceAvailableToShowItem < size) {
        containerSize - size
      } else {
        initialTargetForLeadingEdge
      }

      return offset - targetForLeadingEdge
    }
  }
}
