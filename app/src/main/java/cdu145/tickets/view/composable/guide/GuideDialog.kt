package cdu145.tickets.view.composable.guide

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import cdu145.tickets.viewmodel.GuideDialogViewModel
import org.koin.androidx.compose.getViewModel

private val horizontalPadding = 20.dp

@Composable
fun GuideDialog(
    viewModel: GuideDialogViewModel = getViewModel(),
) {
    Dialog(
        onDismissRequest = { viewModel.dismiss() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .padding(vertical = 20.dp),
            ) {
                GuidePageImage(horizontalPadding = horizontalPadding)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.height(130.dp),
                ) {
                    GuidePageText(horizontalPadding = horizontalPadding)

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = horizontalPadding),
                    ) {
                        PrevGuidePageButton()
                        NextGuidePageButton()
                    }
                }
            }
        }
    }
}