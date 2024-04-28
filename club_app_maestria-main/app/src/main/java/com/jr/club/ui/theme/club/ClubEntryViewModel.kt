package com.juanrolando.coche.ui.theme.coche

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.juanrolando.coche.data.ClubesRepository
import com.juanrolando.coche.data.Coche
import java.text.NumberFormat


class ClubEntryViewModel(private val clubesRepository: ClubesRepository) : ViewModel() {


    var cocheUiState by mutableStateOf(CocheUiState())
        private set

    fun updateUiState(clubDetails: CocheDetails) {
        cocheUiState =
            CocheUiState(clubDetails = clubDetails, isEntryValid = validateInput(clubDetails))
    }

    suspend fun saveClub() {
        if (validateInput()) {
            clubesRepository.insertCoche(cocheUiState.clubDetails.toCoche())
        }
    }

    private fun validateInput(uiState: CocheDetails = cocheUiState.clubDetails): Boolean {
        return with(uiState) {
            descripcion.isNotBlank() && marca.isNotBlank() && modelo.isNotBlank()
        }
    }
}

data class CocheUiState(
    val clubDetails: CocheDetails = CocheDetails(),
    val isEntryValid: Boolean = false
)

data class CocheDetails(
    val id: Int = 0,
    val descripcion: String = "",
    val marca: String = "",
    val modelo: String = "",
    val imagen: Int=0,
    val precio: String = "",
    val anio: Int=0,
)


fun CocheDetails.toCoche(): Coche = Coche(
    id = id,
    name = descripcion,
    country = marca,
    city = modelo,
    imagen = imagen,
    costPlayers = precio.toDoubleOrNull() ?: 0.0,
    yearFundation = anio,
)

fun Coche.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(costPlayers)
}


fun Coche.toCocheUiState(isEntryValid: Boolean = false): CocheUiState = CocheUiState(
    clubDetails = this.toCocheDetails(),
    isEntryValid = isEntryValid
)


fun Coche.toCocheDetails(): CocheDetails = CocheDetails(
    id = id,
    descripcion = name,
    marca = country,
    modelo = city,
    imagen = imagen,
    precio = costPlayers.toString(),
    anio = yearFundation
)
