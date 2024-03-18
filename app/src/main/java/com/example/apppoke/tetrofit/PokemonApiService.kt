package com.example.apppoke.tetrofit

import retrofit2.Call
import retrofit2.http.GET


interface PokemonApiService {
    @GET("pokemon")
    fun obtenerpokemon(): Call<PokemonResponse>


}