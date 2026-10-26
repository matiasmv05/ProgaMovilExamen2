package org.ucb.appp1

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.ucb.appp1.exchange.presentation.screen.ExchangeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        ExchangeScreen()
        org.ucb.appp1.localnotifications.presentation.screen.LocalNotificationsEntry()
    }
}
