package org.ucb.appp1.exchange.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import appp1.shared.generated.resources.Res
import appp1.shared.generated.resources.login_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.exchange.presentation.viewmodel.ExchangeViewModel

@Composable
fun ExchangeScreen(viewModel: ExchangeViewModel = koinViewModel()) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val message by viewModel.message.collectAsState()
        Text(message)
        Button(onClick = { print("Press") }) {
            Text(stringResource(Res.string.login_title))
        }
    }
}
