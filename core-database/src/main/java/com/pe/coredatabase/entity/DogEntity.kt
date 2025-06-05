package com.pe.coredatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class DogEntity(
    @PrimaryKey val id: String,
    val dogName: String,
    val description: String,
    val age: Int,
    val imageUrl: String
)