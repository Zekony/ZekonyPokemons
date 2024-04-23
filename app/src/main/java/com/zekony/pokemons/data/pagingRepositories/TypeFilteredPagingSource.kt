package com.zekony.pokemons.data.pagingRepositories

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zekony.pokemons.R
import com.zekony.pokemons.common.Constants
import com.zekony.pokemons.data.remote.ApiService
import com.zekony.pokemons.data.remote.dto.filteredList.PokemonX
import com.zekony.pokemons.data.util.handlePagingException

class TypeFilteredPagingSource(
    private val type: String,
    private val apiService: ApiService,
    private val context: Context
) : PagingSource<Int, PokemonX>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonX>): Int? {
        return state.anchorPosition?.plus(Constants.PAGE_SIZE)
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PokemonX> {
        try {
            val offset = params.key ?: 0
            val response = apiService.fetchFilteredPokemonsList(type)
                ?: return handlePagingException(Exception(context.getString(R.string.body_is_null)), context)
            val listOf20 =
                response.pokemon.map { it.pokemon }
                    .filterIndexed { index, _ -> index >= offset && index < (offset + Constants.PAGE_SIZE) }
            return LoadResult.Page(
                data = listOf20,
                prevKey = if (offset == 0) null else offset.minus(Constants.PAGE_SIZE),
                nextKey = if (listOf20.size < 20) null else offset.plus(
                    Constants.PAGE_SIZE
                )
            )
        } catch (e: Exception) {
            return handlePagingException(e, context)
        }
    }
}