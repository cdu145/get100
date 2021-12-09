package cdu145.tickets.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cdu145.tickets.view.composable.DialogsOverlay
import cdu145.tickets.view.composable.SolutionCard
import cdu145.tickets.view.composable.TopBar
import cdu145.tickets.view.composable.ticket.AnimatedTicket
import cdu145.view.theme.ComposeTicketsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent { Content() }
    }
}

@Composable
private fun Content() {
    ComposeTicketsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {
            TopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize(),
            ) {
                AnimatedTicket(elevation = 2.dp)
                SolutionCard(elevation = 4.dp, Modifier.fillMaxWidth())
            }
        }

        DialogsOverlay()
    }
}