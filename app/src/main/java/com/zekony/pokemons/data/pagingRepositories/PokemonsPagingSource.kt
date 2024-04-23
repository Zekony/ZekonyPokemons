package com.zekony.pokemons.data.pagingRepositories

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zekony.pokemons.R
import com.zekony.pokemons.common.Constants
import com.zekony.pokemons.data.entity.Pokemon
import com.zekony.pokemons.data.mapper.toPokemon
import com.zekony.pokemons.data.remote.Resource
import com.zekony.pokemons.domain.PokemonApiRepository
import com.zekony.pokemons.domain.PokemonDBRepository
import com.zekony.pokemons.data.util.handlePagingException

class PokemonsPagingSource(
    private val apiRepository: PokemonApiRepository,
    private val dbRepository: PokemonDBRepository,
    private val context: Context
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.plus(Constants.PAGE_SIZE)
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Pokemon> {
         try {
            val offset = params.key ?: 0
            val listFromDB = dbRepository.fetchRangeOfPokemons(offset)
            if (listFromDB.size == Constants.PAGE_SIZE) {
                return LoadResult.Page(
                    data = listFromDB,
                    prevKey = if (offset == 0) null else offset.minus(Constants.PAGE_SIZE),
                    nextKey = offset.plus(Constants.PAGE_SIZE)
                )
            } else {
                val response = apiRepository.fetchPokemonsList(limit = Constants.PAGE_SIZE, offset = offset)
                if (response is Resource.Success && response.data != null) {
                    dbRepository.insertListPokemons(response.data.results.map { it.toPokemon() })
                    return LoadResult.Page(
                        data = response.data.results.sortedBy { it.name }.map { it.toPokemon() },
                        prevKey = if (offset == 0) null else offset.minus(Constants.PAGE_SIZE),
                        nextKey = if (response.data.results.isEmpty()) null else offset.plus(
                            Constants.PAGE_SIZE
                        )
                    )
                } else {
                    return handlePagingException(
                        Exception(
                            response.message ?: context.getString(R.string.unknown_error)
                        ), context
                    )
                }
            }
        } catch (e: Exception) {
            return handlePagingException(e, context)
        }
    }
}