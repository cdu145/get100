package tickets.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun SimpleDialog(
    onDismissRequest: () -> Unit,
    titleText: String,
    contentText: String,
    buttons: @Composable () -> Unit,
) {
    Dialog(onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
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
                    ProvideTextStyle(textStyle) { Text(contentText) }
                }
                Spacer(Modifier.height(24.dp))

                buttons()
            }
        }
    }
}