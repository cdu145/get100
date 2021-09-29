package tickets.hint.available.dialog

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.get
import tickets.actual.Actual
import tickets.hint.NoHintsAvailableDialogState
import tickets.hint.RemainingRestorationTime
import tickets.hint.restoring.RemainingRestorationTime
import tickets.loadable.Loadable
import tickets.loadable.Loadable.NotReady
import tickets.loadable.Loadable.Ready
import tickets.ui.SimpleDialog
import tickets.ui.state.DialogState
import tickets.ui.state.DialogState.Hidden

@Composable
fun NoHintsAvailableDialog(
    state: MutableStateFlow<DialogState> = get(NoHintsAvailableDialogState),
    actualRemainingRestorationTime: Actual<RemainingRestorationTime> = get(RemainingRestorationTime),
) {
    var secondsRemaining by remember { mutableStateOf<Loadable<Int>>(NotReady) }
    LaunchedEffect(null) {
        var remainingRestorationTime = actualRemainingRestorationTime.value()
        do {
            secondsRemaining = Ready(remainingRestorationTime.seconds)
            remainingRestorationTime = remainingRestorationTime.tick()
        } while (!remainingRestorationTime.over)
    }
    SimpleDialog(
        onDismissRequest = { state.value = Hidden },
        titleText = stringResource(R.string.noHintsAvailableDialog_title),
        contentText = contentText(secondsRemaining),
        buttons = {
            Button(onClick = { state.value = Hidden }) {
                Text(stringResource(R.string.noHintsAvailableDialog_dismissText))
            }
        }
    )
}

@Composable
private fun contentText(secondsLeft: Loadable<Int>): String {
    val text = stringResource(R.string.noHintsAvailableDialog_contentText)
    return if (secondsLeft is Ready) {
        text + " ${secondsLeft.value.format()}"
    } else {
        text
    }
}

private fun Int.format(): String = String.format("%02d:%02d", this / 60, this % 60)