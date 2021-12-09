package cdu145.tickets.view.composable.digitcard

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import cdu145.model.Loadable
import cdu145.model.Loadable.Ready
import cdu145.tickets.domain.digits.TicketDigits
import cdu145.tickets.viewmodel.DigitCardsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DigitCards(
    cardsElevation: Dp,
    viewModel: DigitCardsViewModel = getViewModel(),
) {
    val digits by viewModel.digits.collectAsState()
    var shownDigits: TicketDigits by remember { mutableStateOf(TicketDigits.Zeros) }
    val digitsAlpha = remember { Animatable(initialValue = 0f) }
    if (digits is Ready) {
        LaunchedEffect(digits.value) {
            digitsAlpha.animateTo(0f)
            shownDigits = digits.value
            digitsAlpha.animateTo(1f)
        }
    }
    repeat(6) { i ->
        DigitCard(
            digit = shownDigits[i],
            elevation = cardsElevation,
            alpha = { digitsAlpha.value },
            modifier = Modifier.padding(
                start = remember { DigitCard.paddingOfCardAt(i) },
            ),
        )
    }
}

private val Loadable<TicketDigits>.value: TicketDigits
    get() = (this as Ready).value