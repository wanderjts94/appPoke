package com.example.apppoke.tetrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PokemonApiService {
    @GET("pokemon")
    fun obtenerpokemon(@Query("offsrt")offsrt:Int,@Query("linit")linit:Int,): Call<PokemonResponse>


}