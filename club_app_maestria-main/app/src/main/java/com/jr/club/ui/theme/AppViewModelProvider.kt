package com.juanrolando.coche.ui.theme

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.juanrolando.coche.CocheApplication
import com.juanrolando.coche.ui.theme.coche.ClubDetailsViewModel
//import com.juanrolando.coche.ui.theme.coche.CocheEditViewModel
//import com.juanrolando.coche.ui.theme.coche.CocheEntryViewModel
import com.juanrolando.coche.ui.theme.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
                // Initializer for ItemDetailsViewModel
        initializer {
            ClubDetailsViewModel(
                this.createSavedStateHandle(),
                cocheApplication().container.clubesRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(cocheApplication().container.clubesRepository)
        }
    }
}


fun CreationExtras.cocheApplication(): CocheApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CocheApplication)
