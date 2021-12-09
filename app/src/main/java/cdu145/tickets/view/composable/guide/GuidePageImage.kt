package cdu145.tickets.view.composable.guide

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cdu145.tickets.model.guide.Guide
import cdu145.tickets.model.guide.GuidePage
import cdu145.tickets.di.Pages
import cdu145.tickets.viewmodel.GuideDialogViewModel
import org.koin.androidx.compose.getViewModel

private val cornerSize = 6.dp

@Composable
fun GuidePageImage(
    viewModel: GuideDialogViewModel = getViewModel(),
    pages: List<GuidePage> = remember { Guide.scope.get(Pages) },
    horizontalPadding: Dp,
) {
    val pagePosition by viewModel.pagePosition.collectAsState()
    var shownPagePosition by remember { mutableStateOf(0) }
    val alpha = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(pagePosition) {
        alpha.animateTo(0f)
        shownPagePosition = pagePosition
        alpha.animateTo(1f)
    }

    Surface(
        shape = RoundedCornerShape(cornerSize),
        elevation = 0.dp,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
        ),
        modifier = Modifier
            .graphicsLayer { this.alpha = alpha.value }
            .padding(horizontal = horizontalPadding),
    ) {
        Image(
            painter = pages[shownPagePosition].imagePainter,
            contentDescription = null,
        )
    }
}