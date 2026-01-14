package com.example.pr06_lazycomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pr06_lazycomponents.model.Pokemon
import com.example.pr06_lazycomponents.model.PokemonStats
import com.example.pr06_lazycomponents.ui.theme.PR06_LazyComponentsTheme
import com.example.pr06_lazycomponents.view.PokemonDetailScreen
import com.example.pr06_lazycomponents.view.PokemonListScreen
import com.example.pr06_lazycomponents.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PR06_LazyComponentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val viewModel: PokemonViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.ListScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.ListScreen.route) {
                            PokemonListScreen(
                                viewModel = viewModel,
                                onPokemonClick = { pokemon ->
                                    viewModel.selectPokemon(pokemon)
                                    navController.navigate(Screen.DetailScreen.route)
                                }
                            )
                        }

                        composable(Screen.DetailScreen.route) {
                            // Obtenemos el Pokémon del ViewModel
                            val selectedPokemon = viewModel.selectedPokemon.value

                            if (selectedPokemon != null) {
                                PokemonDetailScreen(
                                    pokemon = selectedPokemon,
                                    onBackClick = { navController.navigateUp() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}