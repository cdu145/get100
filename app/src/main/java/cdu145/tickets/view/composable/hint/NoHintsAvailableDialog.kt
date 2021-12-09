package cdu145.tickets.view.composable.hint

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.get
import cdu145.model.Actual
import cdu145.model.DialogState
import cdu145.model.DialogState.Hidden
import cdu145.model.Loadable
import cdu145.model.Loadable.NotReady
import cdu145.model.Loadable.Ready
import cdu145.tickets.di.NoHintsAvailableDialogState
import cdu145.tickets.domain.hint.restoring.RemainingRestorationTime
import cdu145.tickets.di.RemainingRestorationTime as RemainingRestorationTimeQualifier
import cdu145.view.composable.SimpleDialog

@Composable
fun NoHintsAvailableDialog(
    state: MutableStateFlow<DialogState> = get(NoHintsAvailableDialogState),
    actualRemainingRestorationTime: Actual<RemainingRestorationTime> =
        get(RemainingRestorationTimeQualifier),
) {
    var secondsRemaining by remember { mutableStateOf<Loadable<Int>>(NotReady) }
    LaunchedEffect(null) {
        var remainingRestorationTime = actualRemainingRestorationTime.value()
        do {
            secondsRemaining = Ready(remainingRestorationTime.seconds)
            remainingRestorationTime = remainingRestorationTime.tick()
        } while (!remainingRestorationTime.over)
        secondsRemaining = Ready(0)
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