package cdu145.tickets.view.composable.ticket

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

private val upperLineY = 60.dp

fun DrawScope.ticketDrawnOutlinePath(size: Size, cornerRadius: Float): Path {
    return Path().apply {
        addPath(
            ticketShapeOutlinePath(size, cornerRadius),
        )
        val upperLineY = upperLineY.toPx()
        moveTo(0f, upperLineY)
        lineTo(size.width, upperLineY)
    }
}