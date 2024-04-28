package com.juanrolando.coche

import android.app.Application
import com.juanrolando.coche.data.AppContainer

class CocheApplication : Application() {

    lateinit var container: AppContainer

    /*override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }*/
}