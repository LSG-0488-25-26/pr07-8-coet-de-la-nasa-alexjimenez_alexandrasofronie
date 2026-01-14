package com.example.pr06_lazycomponents.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pr06_lazycomponents.model.Pokemon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr06_lazycomponents.viewmodel.PokemonViewModel

@Composable
fun PokemonListScreen(
    onPokemonClick: (Pokemon) -> Unit
) {
    val viewModel: PokemonViewModel = viewModel()
    val pokemonList = viewModel.pokemonList.value ?: emptyList()
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Pokédex",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        if (pokemonList.isEmpty()) {
            Text(
                text = "Cargando Pokémon...",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                items(pokemonList) { pokemon ->
                    Text(
                        text = pokemon.name,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}