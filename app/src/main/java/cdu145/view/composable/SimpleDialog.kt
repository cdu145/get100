package cdu145.view.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun SimpleDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    titleText: String,
    contentText: String,
    buttons: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest,
        properties,
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                modifier = Modifier
                    .width(320.dp)
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                    val textStyle = MaterialTheme.typography.subtitle1
                    ProvideTextStyle(textStyle) { Text(titleText) }
                }

                Spacer(Modifier.height(24.dp))
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    val textStyle = MaterialTheme.typography.body2
                    ProvideTextStyle(textStyle) {
                        Text(
                            contentText,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))

                buttons()
            }
        }
    }
}