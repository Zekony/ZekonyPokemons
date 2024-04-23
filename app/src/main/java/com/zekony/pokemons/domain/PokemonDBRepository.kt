package com.zekony.pokemons.domain

import com.zekony.pokemons.data.entity.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonDBRepository {

    fun fetchAllPokemons(): Flow<List<Pokemon>>

    suspend fun fetchRangeOfPokemons(offset: Int) : List<Pokemon>

    suspend fun insertListPokemons(list: List<Pokemon>)
}