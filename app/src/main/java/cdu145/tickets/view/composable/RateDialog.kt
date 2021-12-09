package cdu145.tickets.view.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import cdu145.model.DialogState.Shown
import cdu145.tickets.R
import cdu145.tickets.model.ratedialog.AppMarketPage
import cdu145.tickets.viewmodel.RateDialogViewModel
import cdu145.view.composable.Margin
import cdu145.view.composable.SimpleDialog
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun RateDialog(
    viewModel: RateDialogViewModel = getViewModel(),
    appMarketPage: AppMarketPage = get(),
) {
    val state by viewModel.state.collectAsState()
    if (state == Shown) {
        SimpleDialog(
            onDismissRequest = { error("Unexpected dismiss request") },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
            ),
            titleText = stringResource(R.string.rateDialog_title),
            contentText = stringResource(R.string.rateDialog_message),
            buttons = {
                Row {
                    OutlinedButton(onClick = { viewModel.complete() }) {
                        Text(
                            stringResource(R.string.rateDialog_maybeLater),
                        )
                    }
                    Margin(4.dp)
                    Button(onClick = { appMarketPage.open(); viewModel.complete() }) {
                        Text(
                            stringResource(R.string.rateDialog_goRate),
                        )
                    }
                }
            }
        )
    }
}