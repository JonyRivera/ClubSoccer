package com.juanrolando.coche.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "club")
data class Coche(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val country: String,
    val city: String,
    val imagen: Int,
    val costPlayers: Double,
    val yearFundation: Int
)