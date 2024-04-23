package com.zekony.pokemons.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zekony.pokemons.R
import com.zekony.pokemons.common.Constants
import com.zekony.pokemons.data.entity.Pokemon
import com.zekony.pokemons.data.pagingRepositories.PokemonsPagingSource
import com.zekony.pokemons.data.pagingRepositories.TypeFilteredPagingSource
import com.zekony.pokemons.data.remote.ApiService
import com.zekony.pokemons.data.remote.Resource
import com.zekony.pokemons.data.remote.dto.NamedApiResourceList
import com.zekony.pokemons.data.remote.dto.PokemonInfo
import com.zekony.pokemons.data.remote.dto.abilityInfo.AbilityInfo
import com.zekony.pokemons.data.remote.dto.filteredList.PokemonX
import com.zekony.pokemons.data.remote.dto.filteredList.TypePokemonInfo
import com.zekony.pokemons.data.remote.dto.filteredList.toTypePokemonInfo
import com.zekony.pokemons.data.util.handleException
import com.zekony.pokemons.domain.PokemonApiRepository
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent.get

class PokemonApiRepositoryImpl(
    private val apiService: ApiService,
    private val context: Context,
) : PokemonApiRepository {

    override fun getPagedPokemonList(): Flow<PagingData<Pokemon>> = Pager(
        config = PagingConfig(
            pageSize = Constants.PAGE_SIZE
        ),
        pagingSourceFactory = {
            get(PokemonsPagingSource::class.java) as PokemonsPagingSource
        }
    ).flow

    override fun getPagedFilteredPokemonList(type: String): Flow<PagingData<PokemonX>> = Pager(
        config = PagingConfig(
            pageSize = Constants.PAGE_SIZE
        ),
        pagingSourceFactory = {
            TypeFilteredPagingSource(type = type, apiService = apiService, context = context)
        }
    ).flow

    override suspend fun fetchPokemonInfo(name: String): Resource<PokemonInfo> {
        return try {
            val call = apiService.fetchPokemonInfo(name)
            if (call == null) Resource.Error(context.getString(R.string.body_is_null)) else
                Resource.Success(call)
        } catch (ex: Exception) {
            handleException(ex, context)
        }
    }

    override suspend fun fetchAbilityInfo(name: String): Resource<AbilityInfo> {
        return try {
            val call = apiService.fetchAbilityInfo(name)
            if (call == null) Resource.Error(context.getString(R.string.body_is_null)) else
                Resource.Success(call)
        } catch (ex: Exception) {
            handleException(ex, context)
        }
    }

    override suspend fun fetchPokemonsList(
        limit: Int,
        offset: Int
    ): Resource<NamedApiResourceList> {
        return try {
            val response = apiService.fetchPokemonsList(limit, offset)
            if (response == null) Resource.Error(context.getString(R.string.body_is_null))
            else Resource.Success(response)
        } catch (ex: Exception) {
            handleException(ex, context)
        }
    }

    override suspend fun fetchFilteredByTypePokemonsList(
        type: String
    ): Resource<TypePokemonInfo> {
        return try {
            val call = apiService.fetchFilteredPokemonsList(type)
            if (call == null) Resource.Error(context.getString(R.string.body_is_null)) else
                Resource.Success(call.toTypePokemonInfo())
        } catch (ex: Exception) {
            handleException(ex, context)
        }
    }
}