@file:Suppress("unused")

package cdu145.view.composable

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnScope.Margin(height: Dp) {
    Spacer(Modifier.height(height))
}

@Composable
fun RowScope.Margin(width: Dp) {
    Spacer(Modifier.width(width))
}