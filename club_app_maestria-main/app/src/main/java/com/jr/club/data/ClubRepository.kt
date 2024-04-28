package com.juanrolando.coche.data

import kotlinx.coroutines.flow.Flow

interface ClubesRepository {

    fun getAllCochesStream(): Flow<List<Coche>>

    fun getCocheStream(id: Int): Flow<Coche?>

    suspend fun insertCoche(item: Coche)

}