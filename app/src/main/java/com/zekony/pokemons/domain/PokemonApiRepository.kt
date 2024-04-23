package com.zekony.pokemons.domain

import androidx.paging.PagingData
import com.zekony.pokemons.data.entity.Pokemon
import com.zekony.pokemons.data.remote.Resource
import com.zekony.pokemons.data.remote.dto.NamedApiResourceList
import com.zekony.pokemons.data.remote.dto.PokemonInfo
import com.zekony.pokemons.data.remote.dto.abilityInfo.AbilityInfo
import com.zekony.pokemons.data.remote.dto.filteredList.PokemonX
import com.zekony.pokemons.data.remote.dto.filteredList.TypePokemonInfo
import kotlinx.coroutines.flow.Flow


interface PokemonApiRepository {

    fun getPagedPokemonList(): Flow<PagingData<Pokemon>>

    fun getPagedFilteredPokemonList(type: String): Flow<PagingData<PokemonX>>

    suspend fun fetchPokemonInfo(name: String): Resource<PokemonInfo>

    suspend fun fetchAbilityInfo(name: String) : Resource<AbilityInfo>

    suspend fun fetchPokemonsList(limit: Int, offset: Int): Resource<NamedApiResourceList>

    suspend fun fetchFilteredByTypePokemonsList(
        type: String
    ): Resource<TypePokemonInfo>
}