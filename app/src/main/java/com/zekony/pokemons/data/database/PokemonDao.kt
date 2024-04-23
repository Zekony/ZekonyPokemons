package com.zekony.pokemons.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zekony.pokemons.data.entity.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM POKEMON_TABLE_NAME")
    fun fetchAllPokemons(): Flow<List<Pokemon>>

    @Query("SELECT * FROM POKEMON_TABLE_NAME WHERE id > :offset and id <= :highOffset")
    suspend fun fetchRangeOfPokemons(offset: Int, highOffset: Int): List<Pokemon>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertListPokemons(list: List<Pokemon>)
}