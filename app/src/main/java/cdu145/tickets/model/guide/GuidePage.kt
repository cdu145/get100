package cdu145.tickets.model.guide

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

class GuidePage(
    private val imageResId: Int,
    private val textResId: Int,
) {

    val imagePainter: Painter
        @Composable
        get() = painterResource(imageResId)

    val text: String
        @Composable
        get() = stringResource(textResId)
}