package com.zekony.pokemons.di

import androidx.room.Room
import com.zekony.pokemons.common.Constants
import com.zekony.pokemons.data.database.PokemonDatabase
import com.zekony.pokemons.data.pagingRepositories.PokemonsPagingSource
import com.zekony.pokemons.data.remote.ApiService
import com.zekony.pokemons.domain.PokemonApiRepository
import com.zekony.pokemons.data.repository.PokemonApiRepositoryImpl
import com.zekony.pokemons.domain.PokemonDBRepository
import com.zekony.pokemons.data.repository.PokemonDBRepositoryImpl
import com.zekony.pokemons.ui.filterScreen.FilterViewModel
import com.zekony.pokemons.ui.infoScreen.InfoViewModel
import com.zekony.pokemons.ui.pokemonsScreen.PokemonViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainModule {
    val module = module {
        single { Dispatchers.IO }
        single<ApiService> {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
        single {
            Room.databaseBuilder(
                get(),
                PokemonDatabase::class.java,
                Constants.POKEMON_TABLE_NAME
            )
                .build()
        }
        single {
            get<PokemonDatabase>().PokemonDao()
        }
        single<PokemonDBRepository> {
            PokemonDBRepositoryImpl(
                dao = get()
            )
        }
        single<PokemonApiRepository> {
            PokemonApiRepositoryImpl(
                apiService = get(),
                context = androidApplication(),
            )
        }
        single {
            PokemonsPagingSource(
                apiRepository = get(),
                dbRepository = get(),
                context = androidApplication()
            )
        }
        viewModelOf(::PokemonViewModel)
        viewModelOf(::InfoViewModel)
        viewModelOf(::FilterViewModel)
    }
}