package cdu145.tickets.view.composable.digitcard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cdu145.tickets.view.composable.gapbuttons.SolutionGapButton

object DigitCard {

    val Width = 36.dp
    val Height = 48.dp

    private val CornerSize = 4.dp

    private val FontSize = 26.sp

    @SuppressLint("ComposableNaming")
    @Composable
    operator fun invoke(
        digit: Int,
        elevation: Dp,
        alpha: () -> Float,
        modifier: Modifier = Modifier,
    ) {
        Surface(
            shape = RoundedCornerShape(CornerSize),
            elevation = elevation,
            modifier = modifier,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(Width, Height),
            ) {
                Text(
                    text = digit.toString(),
                    fontSize = FontSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer { this.alpha = alpha() },
                )
            }
        }
    }

    fun paddingOfCardAt(position: Int): Dp {
        return (Width + SolutionGapButton.Size - SolutionGapButton.Overlap * 2) * position
    }
}