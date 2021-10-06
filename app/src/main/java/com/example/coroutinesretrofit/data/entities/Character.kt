package com.fclarke.coroutinesretrofit.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(//Character
    @PrimaryKey
    val id: Long,
    var content: String? = null
)