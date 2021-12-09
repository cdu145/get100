package cdu145.tickets.view.composable.signbutton

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import cdu145.tickets.domain.solution.sign.SolutionSign
import cdu145.tickets.viewmodel.SignButtonsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SignButtons(
    spaceBetween: Dp,
    viewModel: SignButtonsViewModel = getViewModel(),
) {
    Row {
        val shown by viewModel.shown.collectAsState()
        val shownRatio by animateFloatAsState(if (shown) 1f else 0f)
        SolutionSign.values().forEachIndexed { index, sign ->
            SignButton(sign, shownRatio)
            if (index < 4) {
                Spacer(Modifier.width(spaceBetween))
            }
        }
    }
}