package cdu145.tickets.view.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import cdu145.tickets.viewmodel.ClearSolutionButtonViewModel
import cdu145.view.composable.BottomButton
import org.koin.androidx.compose.getViewModel

@Composable
fun ClearSolutionButton(
    viewModel: ClearSolutionButtonViewModel = getViewModel(),
) {
    val shown by viewModel.shown.collectAsState()
    val shownRatio by animateFloatAsState(targetValue = if (shown) 1f else 0f)
    BottomButton(
        onClick = { viewModel.clearSolution() },
        iconGravity = BottomButton.IconGravity.Left,
        modifier = Modifier.graphicsLayer { alpha = shownRatio },
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_clear),
                contentDescription = null,
            )
        },
        text = {
            Text(
                stringResource(R.string.clearSolutionButton_text),
            )
        },
    )
}