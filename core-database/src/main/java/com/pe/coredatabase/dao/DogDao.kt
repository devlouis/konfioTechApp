package com.pe.coredatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pe.coredatabase.entity.DogEntity

@Dao
interface DogDao {
    @Query("SELECT * FROM dogs")
    suspend fun getAllDogs(): List<DogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogs: List<DogEntity>)

    @Query("DELETE FROM dogs")
    suspend fun clearAll()
}