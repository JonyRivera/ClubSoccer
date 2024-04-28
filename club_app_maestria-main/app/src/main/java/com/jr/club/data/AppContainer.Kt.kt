package com.juanrolando.coche.data
import android.content.Context

interface AppContainer {
    val clubesRepository: ClubesRepository
}

class AppDataContainer(
    private val context: Context, override val clubesRepository: ClubesRepository
) : AppContainer {

    val cochesRepository: ClubesRepository by lazy {
        OfflineCochesRepository(CocheDataBase.getDatabase(context).cocheDao())
    }
}