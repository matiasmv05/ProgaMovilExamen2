package org.ucb.appp1.movies.presentation.viewmodel

sealed interface DollarEffect {
    data class ShowToast(val message: String) : DollarEffect
}
