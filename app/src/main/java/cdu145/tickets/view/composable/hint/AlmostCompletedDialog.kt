package cdu145.tickets.view.composable.hint

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cdu145.model.DialogState
import cdu145.model.DialogState.Hidden
import cdu145.model.DialogState.Shown
import cdu145.tickets.R
import cdu145.tickets.di.AlmostCompletedDialogState
import cdu145.tickets.domain.hint.AlmostCompletedDialog
import cdu145.view.composable.SimpleDialog
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.get

@Composable
fun AlmostCompletedDialog(
    state: MutableStateFlow<DialogState> = get(AlmostCompletedDialogState),
) {
    SimpleDialog(
        onDismissRequest = { state.value = Hidden },
        titleText = stringResource(R.string.almostCompletedDialog_title),
        contentText = stringResource(R.string.almostCompletedDialog_message),
        buttons = {
            Button(onClick = { state.value = Hidden }) {
                Text(stringResource(R.string.almostCompletedDialog_close))
            }
        }
    )
}

class AlmostCompletedDialogImpl(
    private val state: MutableStateFlow<DialogState>,
) : AlmostCompletedDialog {

    override fun show() {
        state.value = Shown
    }
}