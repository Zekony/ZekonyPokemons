package com.zekony.pokemons.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.zekony.pokemons.common.Constants

@Entity(
    tableName = Constants.POKEMON_TABLE_NAME,
    indices = [Index(value = ["name"], unique = true)])
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val url: String
)