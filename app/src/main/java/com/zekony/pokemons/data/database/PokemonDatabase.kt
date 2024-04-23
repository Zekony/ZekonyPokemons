package com.zekony.pokemons.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zekony.pokemons.data.entity.Pokemon

@Database(
    entities = [Pokemon::class],
    version = 1
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun PokemonDao(): PokemonDao
}

