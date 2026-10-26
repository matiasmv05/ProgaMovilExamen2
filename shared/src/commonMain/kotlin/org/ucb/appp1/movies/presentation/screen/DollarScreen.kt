package org.ucb.appp1.movies.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.movies.presentation.viewmodel.DollarViewModel

@Composable
fun DollarScreen(
    viewModel: DollarViewModel = koinViewModel()
) {
    Column {
        Text("Dollar Screen ${viewModel.state}")
    }
}
