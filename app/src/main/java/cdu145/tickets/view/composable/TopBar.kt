package cdu145.tickets.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cdu145.tickets.R
import cdu145.view.theme.ComposeTicketsTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h6,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun TicketsTopBarPreview() {
    ComposeTicketsTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            TopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            )
        }
    }
}