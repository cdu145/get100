package cdu145.tickets.view.composable.ticket

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path

fun ticketShapeOutlinePath(size: Size, cornerRadius: Float): Path {
    return Path().apply {
        moveTo(0f, size.height)
        lineTo(0f, cornerRadius)
        arcTo(
            Rect(
                left = -cornerRadius,
                top = -cornerRadius,
                right = cornerRadius,
                bottom = cornerRadius
            ),
            startAngleDegrees = 90.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false,
        )
        lineTo(size.width - cornerRadius, 0f)
        arcTo(
            Rect(
                left = size.width - cornerRadius,
                top = -cornerRadius,
                right = size.width + cornerRadius,
                bottom = cornerRadius
            ),
            startAngleDegrees = 180.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false,
        )
        lineTo(size.width, size.height)
        close()
    }
}