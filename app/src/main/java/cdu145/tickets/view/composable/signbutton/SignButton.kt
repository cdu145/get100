package cdu145.tickets.view.composable.signbutton

import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cdu145.tickets.R
import cdu145.tickets.domain.solution.sign.SolutionSign
import cdu145.tickets.domain.solution.sign.SolutionSign.*
import cdu145.tickets.viewmodel.SignButtonsViewModel
import cdu145.view.composable.CircleButton
import org.koin.androidx.compose.getViewModel

private val Size = 42.dp

@Composable
fun SignButton(
    sign: SolutionSign,
    shownRatio: Float,
    viewModel: SignButtonsViewModel = getViewModel(),
) {
    CircleButton(
        onClick = { viewModel.chooseSign(sign) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
        ),
        modifier = Modifier
            .size(Size)
            .graphicsLayer { scaleX = shownRatio; scaleY = shownRatio },
    ) {
        Text(sign.text)
    }
}

private val SolutionSign.text: String
    @Composable
    get() = when (this) {
        Plus -> stringResource(R.string.signs_plus)
        Minus -> stringResource(R.string.signs_minus)
        Times -> stringResource(R.string.signs_times)
        Div -> stringResource(R.string.signs_div)
        None -> stringResource(R.string.signs_none)
    }