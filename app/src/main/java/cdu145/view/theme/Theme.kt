package cdu145.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun ComposeTicketsTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = lightColors(
            primary = DeepOrange200,
            primaryVariant = DeepOrange400,
            secondary = Cyan200,
            secondaryVariant = Cyan100,
            background = Grey200,
        ),
        typography = Typography,
        shapes = Shapes,
        content,
    )
}