package cdu145.tickets.guide

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import cdu145.tickets.guide.PrevPageButtonState.Shown

@Composable
fun PrevPageButton(
    viewModel: ViewModel = getViewModel(),
) {
    val state by viewModel.prevButtonState.collectAsState()
    val alpha by animateFloatAsState(targetValue = if (state == Shown) 1f else 0f)
    Button(
        onClick = { viewModel.showPrevPage() },
        enabled = state == Shown,
        modifier = Modifier.graphicsLayer(alpha = alpha),
    ) {
        Text(
            text = stringResource(R.string.guideDialog_prev),
        )
    }
}

enum class PrevPageButtonState { Shown, Hidden }