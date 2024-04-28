package com.juanrolando.coche.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CocheDao {
    @Query("SELECT * from club ORDER BY name ASC")
    fun getAllCoches(): Flow<List<Coche>>

    @Query("SELECT * from club WHERE id = :id")
    fun getCoche(id: Int): Flow<Coche>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(coche: Coche)

    @Query("DELETE FROM club")
    suspend fun deleteAll()
}