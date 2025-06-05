package com.pe.coredatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pe.coredatabase.dao.DogDao
import com.pe.coredatabase.entity.DogEntity

@Database(entities = [DogEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
}