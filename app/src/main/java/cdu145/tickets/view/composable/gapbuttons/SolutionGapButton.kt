package cdu145.tickets.view.composable.gapbuttons

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cdu145.tickets.R
import cdu145.tickets.domain.solution.sign.SolutionSign
import cdu145.tickets.view.composable.digitcard.DigitCard
import cdu145.view.composable.CircleButton

object SolutionGapButton {

    val Size = 32.dp

    val Overlap = 6.dp

    @SuppressLint("ComposableNaming")
    @Composable
    operator fun invoke(
        onClick: () -> Unit,
        sign: SolutionSign,
        elevation: ButtonElevation,
        backgroundColor: Color,
        modifier: Modifier,
    ) {
        CircleButton(
            onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
            elevation = elevation,
            modifier = modifier.size(Size),
        ) {
            Text(sign.text)
        }
    }

    private val SolutionSign.text: String
        @Composable
        get() = when (this) {
            SolutionSign.Plus -> stringResource(R.string.signs_plus)
            SolutionSign.Minus -> stringResource(R.string.signs_minus)
            SolutionSign.Times -> stringResource(R.string.signs_times)
            SolutionSign.Div -> stringResource(R.string.signs_div)
            SolutionSign.None -> ""
        }

    fun paddingOfButtonAt(position: Int): Dp {
        return DigitCard.paddingOfCardAt(position) + DigitCard.Width - Overlap
    }
}