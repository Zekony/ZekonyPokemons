package com.zekony.pokemons.data.repository

import com.zekony.pokemons.data.database.PokemonDao
import com.zekony.pokemons.data.entity.Pokemon
import com.zekony.pokemons.domain.PokemonDBRepository
import kotlinx.coroutines.flow.Flow

class PokemonDBRepositoryImpl(
    private val dao: PokemonDao
) : PokemonDBRepository {

    override fun fetchAllPokemons(): Flow<List<Pokemon>> = dao.fetchAllPokemons()

    override suspend fun fetchRangeOfPokemons(offset: Int): List<Pokemon> {
        return dao.fetchRangeOfPokemons(offset, offset + 20)
    }

    override suspend fun insertListPokemons(list: List<Pokemon>) {
        dao.insertListPokemons(list)
    }
}