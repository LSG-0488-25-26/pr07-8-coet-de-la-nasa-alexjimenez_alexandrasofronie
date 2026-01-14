package com.example.pr06_lazycomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pr06_lazycomponents.ui.theme.PR06_LazyComponentsTheme
import com.example.pr06_lazycomponents.view.PokemonDetailScreen
import com.example.pr06_lazycomponents.view.PokemonListScreen
import com.example.pr06_lazycomponents.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PR06_LazyComponentsTheme {
                PokemonApp()
            }
        }
    }
}

@Composable
fun PokemonApp() {
    val navController = rememberNavController()
    val viewModel: PokemonViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.ListScreen.route
    ) {
        composable(Screen.ListScreen.route) {

            PokemonListScreen(
                viewModel = viewModel,
                onPokemonClick = { pokemon ->
                    viewModel.selectPokemon(pokemon)
                    // Navegamos sin pasar argumentos
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