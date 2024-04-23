package com.zekony.pokemons.data.remote

import com.zekony.pokemons.data.remote.dto.NamedApiResourceList
import com.zekony.pokemons.data.remote.dto.PokemonInfo
import com.zekony.pokemons.data.remote.dto.abilityInfo.AbilityInfo
import com.zekony.pokemons.data.remote.dto.filteredList.FilteredPokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    suspend fun fetchPokemonsList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): NamedApiResourceList?

    @GET("pokemon/{name}/")
    suspend fun fetchPokemonInfo(
        @Path("name") name: String
    ): PokemonInfo?

    @GET("type/{type}/")
    suspend fun fetchFilteredPokemonsList(
        @Path("type") type: String
    ): FilteredPokemonList?

    @GET("ability/{name}/")
    suspend fun fetchAbilityInfo(
        @Path("name") name: String
    ): AbilityInfo?
}