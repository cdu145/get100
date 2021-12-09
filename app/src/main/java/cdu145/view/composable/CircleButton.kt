package cdu145.view.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick,
        modifier = modifier,
        shape = CircleShape,
        enabled = enabled,
        colors = colors,
        elevation = elevation,
        contentPadding = PaddingValues(0.dp),
        content = content,
    )
}