package com.example.apppoke


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apppoke.databinding.ItempokemonBinding
import com.example.apppoke.tetrofit.Pokemon
import java.util.ArrayList

class PokemonAdapter :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){
        private var lista = ArrayList<Pokemon>()
    inner class  ViewHolder(val bindimg: ItempokemonBinding)
        :RecyclerView.ViewHolder(bindimg.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindimg= ItempokemonBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return ViewHolder(bindimg)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(lista[position]) {
                bindimg.twnompokemon.text = name
                val arrayUrl = url.split("/")
                Glide.with(itemView)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${arrayUrl[arrayUrl.size -2]}.png")
                    .into(bindimg.ivpokemon)


            }
        }
    }
    fun postPokemones(nuevosPokemones: List<Pokemon>){
        lista.addAll(nuevosPokemones)
        notifyDataSetChanged()
    }
    override fun getItemCount() = lista.size






}