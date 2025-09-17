package dev.taked137.composefortvsample.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.relocation.BringIntoViewModifierNode
import androidx.compose.ui.relocation.bringIntoView

fun Modifier.scrollParentOnChildFocus(): Modifier =
  this then ScrollParentOnChildFocusElement

private data object ScrollParentOnChildFocusElement :
  ModifierNodeElement<ScrollParentOnChildFocusNode>() {
  override fun create() = ScrollParentOnChildFocusNode()

  override fun update(node: ScrollParentOnChildFocusNode) = Unit

  override fun InspectorInfo.inspectableProperties() {
    name = "scrollParentOnChildFocus"
  }
}

private class ScrollParentOnChildFocusNode :
  BringIntoViewModifierNode,
  Modifier.Node() {

  override suspend fun bringIntoView(
    childCoordinates: LayoutCoordinates,
    boundsProvider: () -> Rect?,
  ) {
    bringIntoView()
  }
}
