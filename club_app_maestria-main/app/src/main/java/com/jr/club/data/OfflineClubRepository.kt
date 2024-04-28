package com.juanrolando.coche.data

import kotlinx.coroutines.flow.Flow

class OfflineCochesRepository(private val cocheDao: CocheDao) : ClubesRepository {
    override fun getAllCochesStream(): Flow<List<Coche>> = cocheDao.getAllCoches()

    override fun getCocheStream(id: Int): Flow<Coche?> = cocheDao.getCoche(id)

    override suspend fun insertCoche(coche: Coche) = cocheDao.insert(coche)

}