package com.example.pr06_lazycomponents.repository

import com.example.pr06_lazycomponents.model.Pokemon
import com.example.pr06_lazycomponents.R

class PokemonRepository {
    fun getPokemonList(): MutableList<Pokemon> {
        val pokedex: MutableList<Pokemon> = mutableListOf()

        pokedex.add(Pokemon("Bulbasaur", "Grass", R.drawable.bulbasaur))
        pokedex.add(Pokemon("Charmander", "Fire", R.drawable.charmander))
        pokedex.add(Pokemon("Squirtle", "Water", R.drawable.squirtle))
        pokedex.add(Pokemon("Jigglypuff", "Fairy", R.drawable.jigglypuff))
        pokedex.add(Pokemon("Meowth", "Normal", R.drawable.meowth))
        pokedex.add(Pokemon("Psyduck", "Water", R.drawable.psyduck))
        pokedex.add(Pokemon("Golbat", "Poison", R.drawable.golbat))
        pokedex.add(Pokemon("Pikachu", "Electric", R.drawable.pikachu))
        pokedex.add(Pokemon("Rattata", "Normal", R.drawable.rattata))
        pokedex.add(Pokemon("Spearow", "Normal", R.drawable.spearow))
        pokedex.add(Pokemon("Ekans", "Poison", R.drawable.ekans))
        pokedex.add(Pokemon("Vulpix", "Fire", R.drawable.vulpix))
        pokedex.add(Pokemon("Geodude", "Rock", R.drawable.geodude))
        pokedex.add(Pokemon("Tentacruel", "Water", R.drawable.tentacruel))

        return pokedex
    }
}