package com.zekony.pokemons.data.mapper

import com.zekony.pokemons.data.entity.Pokemon
import com.zekony.pokemons.data.remote.dto.NamedApiResource

fun NamedApiResource.toPokemon(): Pokemon =
    Pokemon(
        name = name,
        url = url
    )