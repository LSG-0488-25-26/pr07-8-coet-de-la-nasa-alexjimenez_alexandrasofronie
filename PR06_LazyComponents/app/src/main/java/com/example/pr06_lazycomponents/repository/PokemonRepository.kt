package com.example.pr06_lazycomponents.repository

import com.example.pr06_lazycomponents.model.Pokemon
import com.example.pr06_lazycomponents.model.PokemonStats
import com.example.pr06_lazycomponents.R

class PokemonRepository {
    fun getPokemonList(): MutableList<Pokemon> {
        val pokedex: MutableList<Pokemon> = mutableListOf()

        pokedex.add(Pokemon(
            name = "Bulbasaur",
            type = "Grass",
            image = R.drawable.bulbasaur,
            stats = PokemonStats(45, 49, 49, 65, 65, 45),
        ))

        pokedex.add(Pokemon(
            name = "Charmander",
            type = "Fire",
            image = R.drawable.charmander,
            stats = PokemonStats(39, 52, 43, 60, 50, 65),
        ))

        pokedex.add(Pokemon(
            name = "Squirtle",
            type = "Water",
            image = R.drawable.squirtle,
            stats = PokemonStats(44, 48, 65, 50, 64, 43),
        ))

        pokedex.add(Pokemon(
            name = "Pikachu",
            type = "Electric",
            image = R.drawable.pikachu,
            stats = PokemonStats(35, 55, 40, 50, 50, 90),
        ))

        pokedex.add(Pokemon(
            name = "Jigglypuff",
            type = "Fairy",
            image = R.drawable.jigglypuff,
            stats = PokemonStats(115, 45, 20, 45, 25, 20),
        ))

        pokedex.add(Pokemon(
            name = "Meowth",
            type = "Normal",
            image = R.drawable.meowth,
            stats = PokemonStats(40, 45, 35, 40, 40, 90),
        ))

        pokedex.add(Pokemon(
            name = "Psyduck",
            type = "Water",
            image = R.drawable.psyduck,
            stats = PokemonStats(50, 52, 48, 65, 50, 55),
        ))

        pokedex.add(Pokemon(
            name = "Golbat",
            type = "Poison",
            image = R.drawable.golbat,
            stats = PokemonStats(75, 80, 70, 65, 75, 90),
        ))

        // Añade los demás Pokémon de la misma forma...
        pokedex.add(Pokemon(
            name = "Rattata",
            type = "Normal",
            image = R.drawable.rattata,
            stats = PokemonStats(30, 56, 35, 25, 35, 72),
        ))

        pokedex.add(Pokemon(
            name = "Spearow",
            type = "Normal",
            image = R.drawable.spearow,
            stats = PokemonStats(40, 60, 30, 31, 31, 70),
        ))

        pokedex.add(Pokemon(
            name = "Ekans",
            type = "Poison",
            image = R.drawable.ekans,
            stats = PokemonStats(35, 60, 44, 40, 54, 55),
        ))

        pokedex.add(Pokemon(
            name = "Vulpix",
            type = "Fire",
            image = R.drawable.vulpix,
            stats = PokemonStats(38, 41, 40, 50, 65, 65),
        ))

        pokedex.add(Pokemon(
            name = "Geodude",
            type = "Rock",
            image = R.drawable.geodude,
            stats = PokemonStats(40, 80, 100, 30, 30, 20),
        ))

        pokedex.add(Pokemon(
            name = "Tentacruel",
            type = "Water",
            image = R.drawable.tentacruel,
            stats = PokemonStats(80, 70, 65, 80, 120, 100),
        ))

        return pokedex
    }
}