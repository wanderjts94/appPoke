package com.example.apppoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppoke.databinding.ActivityMainBinding
import com.example.apppoke.tetrofit.PokemonApiService
import com.example.apppoke.tetrofit.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    private lateinit var apiRetrofit: Retrofit
    private lateinit var pokemonAdapter:PokemonAdapter

    private var offsrt=0
    private var linit=20
    private var  puedecargar= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiRetrofit=Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        pokemonAdapter= PokemonAdapter()
        binding.rvLista.layoutManager=GridLayoutManager(
            applicationContext,3
        )
        binding.rvLista.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy>0){
                    val itemsvisibles= binding.rvLista.layoutManager!!.childCount
                    val itemslocales= binding.rvLista.layoutManager!!.itemCount
                    val primeritemvisible=(binding.rvLista.layoutManager!! as GridLayoutManager)
                            .findFirstCompletelyVisibleItemPosition()
                if(puedecargar){
                    if(itemsvisibles + primeritemvisible >= itemslocales){
                        puedecargar=false
                        offsrt+=20
                        obtenerPokemonRetrifit()
                    }

                }

                }
            }

        })
        binding.rvLista.adapter=pokemonAdapter
        obtenerPokemonRetrifit()

    }

    private fun obtenerPokemonRetrifit() {
        var service = apiRetrofit.create(PokemonApiService::class.java)
        var pokemonResponse = service.obtenerpokemon(offsrt, linit)
        pokemonResponse.enqueue(object : Callback<PokemonResponse>{
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                puedecargar=true
                pokemonAdapter.postPokemones(
                    response.body()!!.results)
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}