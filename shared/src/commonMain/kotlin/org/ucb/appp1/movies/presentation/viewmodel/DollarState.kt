package org.ucb.appp1.movies.presentation.viewmodel

import org.ucb.appp1.movies.domain.model.DollarModel

data class DollarState(
    val list: List<DollarModel> = emptyList()
)
