package com.juanrolando.coche.ui.theme.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanrolando.coche.data.ClubesRepository
import com.juanrolando.coche.data.Coche
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class HomeViewModel(cochesRepository: ClubesRepository) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        cochesRepository.getAllCochesStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(val cocheList: List<Coche> = listOf())