package com.juanrolando.coche.ui.theme.coche

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanrolando.coche.data.ClubesRepository
//import com.juanrolando.coche.data.ClubesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class ClubDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val clubesRepository: ClubesRepository,
) : ViewModel() {

    private val clubId: Int = checkNotNull(savedStateHandle[ClubDetailsDestination.clubIdArg])

    val uiState: StateFlow<ClubDetailsUiState> =
        clubesRepository.getCocheStream(clubId)
            .filterNotNull()
            .map {
                ClubDetailsUiState(cocheDetails = it.toCocheDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ClubDetailsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ClubDetailsUiState(
    val outOfStock: Boolean = true,
    val cocheDetails: CocheDetails = CocheDetails()
)

