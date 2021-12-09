package cdu145.tickets.view.composable.gapbuttons

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cdu145.model.Loadable
import cdu145.model.Loadable.Ready
import cdu145.tickets.domain.solution.Solution
import cdu145.tickets.domain.solution.sign.SolutionSign
import cdu145.tickets.domain.solution.sign.SolutionSign.None
import cdu145.tickets.model.GapPosition
import cdu145.tickets.view.composable.digitcard.DigitCard
import cdu145.tickets.viewmodel.SolutionGapsViewModel
import org.koin.androidx.compose.getViewModel

private val highlightedGapColor: Color
    @Composable
    get() = MaterialTheme.colors.secondaryVariant

@Composable
fun SolutionGapButtons(
    buttonsElevation: ButtonElevation,
    viewModel: SolutionGapsViewModel = getViewModel(),
) {
    val highlightedPosition by viewModel.highlightedPosition.collectAsState()
    val shownSolution by viewModel.shownSolution.collectAsState()
    val justOpenedPosition by viewModel.justOpenedPosition.collectAsState()
    repeat(5) { i ->
        SolutionGapButton(
            onClick = { viewModel.highlightGapAt(i) },
            sign = shownSolution.signAtOrNone(i),
            elevation = buttonsElevation,
            backgroundColor = when (val gapPosition = i.asGapPosition()) {
                justOpenedPosition -> justOpenedGapBackgroundColor(gapPosition, highlightedPosition)
                highlightedPosition -> highlightedGapColor
                else -> MaterialTheme.colors.surface
            },
            modifier = Modifier.padding(
                start = remember { SolutionGapButton.paddingOfButtonAt(i) },
                top = (DigitCard.Height - SolutionGapButton.Size) / 2,
            ),
        )
    }
}

private fun Loadable<Solution>.signAtOrNone(position: Int): SolutionSign {
    return (this as? Ready)?.value?.signAt(position) ?: None
}

private fun Int.asGapPosition(): GapPosition = GapPosition.Some(this)

@Composable
private fun justOpenedGapBackgroundColor(
    position: GapPosition,
    highlightedPosition: GapPosition,
): Color {
    val initialColor = MaterialTheme.colors.secondary
    val targetColor = if (position == highlightedPosition) {
        highlightedGapColor
    } else {
        MaterialTheme.colors.surface
    }
    val color = remember(position) { Animatable(initialColor) }
    LaunchedEffect(position) {
        color.run {
            snapTo(initialColor)
            animateTo(targetColor, animationSpec = tween(durationMillis = 3_500))
        }
    }
    return color.value
}