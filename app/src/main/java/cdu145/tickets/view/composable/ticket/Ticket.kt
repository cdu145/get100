package cdu145.tickets.view.composable.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cdu145.tickets.domain.digits.TicketDigits
import cdu145.view.theme.AveriaFontFamily
import cdu145.view.theme.InkBlue
import cdu145.view.theme.InkGreen

private val width = 170.dp
private val height = 195.dp

private val cornerRadius = 18.dp

@Composable
fun Ticket(
    digits: TicketDigits,
    elevation: Dp,
    numberRotation: Float,
    animationTransitionRatio: () -> Float,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width, height)
            .graphicsLayer {
                shape = TicketShape(cornerRadius)
                shadowElevation = elevation.toPx()
                translationY = height.toPx() * translationRatio(animationTransitionRatio())
                clip = true
            }
            .background(MaterialTheme.colors.primary)
            .drawBehind {
                scale(0.9f) {
                    drawPath(
                        ticketDrawnOutlinePath(
                            size,
                            cornerRadius.toPx(),
                        ),
                        color = InkGreen,
                        style = Stroke(
                            width = 3.dp.toPx(),
                        ),
                    )
                }
            },
    ) {
        Text(
            text = remember(digits) { numberText(digits) },
            fontFamily = AveriaFontFamily,
            color = InkBlue,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 60.dp)
                .graphicsLayer(rotationZ = numberRotation),
        )
    }
}

private fun numberText(digits: TicketDigits): String {
    return buildString {
        repeat(5) { i ->
            append(digits[i])
            append(" ")
        }
        append(digits[5])
    }
}

private const val baseTranslationRatio = 0.075f

private fun translationRatio(animationTranslationRatio: Float): Float {
    return ((1f - baseTranslationRatio) * animationTranslationRatio) + baseTranslationRatio
}