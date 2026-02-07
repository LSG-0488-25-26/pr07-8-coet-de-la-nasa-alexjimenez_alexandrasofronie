package com.example.pr06_lazycomponents

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pr06_lazycomponents.model.Media
import com.example.pr06_lazycomponents.model.MediaDetails
import com.example.pr06_lazycomponents.model.MediaType
import com.example.pr06_lazycomponents.ui.theme.PR06_LazyComponentsTheme
import com.example.pr06_lazycomponents.view.MediaDetailScreen
import com.example.pr06_lazycomponents.view.MediaListScreen
import com.example.pr06_lazycomponents.view.components.MyBottomBar
import com.example.pr06_lazycomponents.viewmodel.MediaViewModel
import com.example.pr06_lazycomponents.view.FavoriteScreen
import com.example.pr06_lazycomponents.view.components.MyTopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PR06_LazyComponentsTheme {
                val navController = rememberNavController()
                val viewModel: MediaViewModel = viewModel()

                ScaffoldView(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun ScaffoldView(
    navController: NavHostController,
    viewModel: MediaViewModel,
) {
    // Observamos el estado de la SearchBar desde el ViewModel
    val isSearchBarVisible by viewModel.isSearchBarVisible.observeAsState(initial = false)
    val currentSearchQuery by viewModel.currentSearchQuery.observeAsState(initial = "")

    Scaffold(
        topBar = {
            // Obtenemos la ruta actual
            val currentRoute = navController.currentDestination?.route

            when (currentRoute) {
                Screen.DetailScreen.route -> {
                    val media = viewModel.selectedMedia.value
                    val title = viewModel.selectedMedia.value?.title ?: "Detalles"
                    MyTopAppBar(
                        title = title,
                        showBackIcon = true,
                        showSearchIcon = false,
                        onBackClick = { navController.popBackStack() }
                    )
                }
                Screen.FavoriteScreen.route -> {
                    MyTopAppBar(
                        title = "❤️ Favoritos",
                        showBackIcon = false,
                        showSearchIcon = false
                    )
                }
                else -> {
                    val title = if (viewModel.currentSearchQuery.value?.isNotEmpty() == true) {
                        "🔍 \"${viewModel.currentSearchQuery.value}\""
                    } else {
                        "🎬 Películas & Series"
                    }

                    MyTopAppBar(
                        title = title,
                        showBackIcon = false,
                        showSearchIcon = true,
                        onSearchClick = {
                            viewModel.toggleSearchBarVisibility()
                        }
                    )
                }
            }
        },
        bottomBar = {
            MyBottomBar(navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MyAppNavHost(
                navController = navController,
                viewModel = viewModel,
                isSearchBarVisible = isSearchBarVisible
            )
        }
    }
}

@Composable
fun MyAppNavHost(
    navController: NavHostController,
    viewModel: MediaViewModel,
    isSearchBarVisible: Boolean = false
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ListScreen.route
    ) {
        composable(Screen.ListScreen.route) {
            LaunchedEffect(Unit) {
                if (viewModel.currentSearchQuery.value?.isEmpty() == true) {
                    viewModel.setSearchBarVisibility(false)
                }
            }

            MediaListScreen(
                viewModel = viewModel,
                isSearchBarVisible = isSearchBarVisible,
                onMediaClick = { media ->
                    viewModel.selectMedia(media)
                    navController.navigate(Screen.DetailScreen.route)
                }
            )
        }
        composable(Screen.DetailScreen.route) {
            LaunchedEffect(Unit) {
                viewModel.setSearchBarVisibility(false)
            }
            // Obtenemos la película/serie del ViewModel
            val selectedMedia = viewModel.selectedMedia.value
            if (selectedMedia != null) {
                MediaDetailScreen(
                    media = selectedMedia,
                    onBackClick = { navController.navigateUp() },
                    viewModel = viewModel
                )
            }
        }

        composable(Screen.FavoriteScreen.route) {
            LaunchedEffect(Unit) {
                viewModel.setSearchBarVisibility(false)
            }
            FavoriteScreen(
                onMediaClick = { media ->
                    viewModel.selectMedia(media)
                    navController.navigate(Screen.DetailScreen.route)
                },
                viewModel = viewModel
            )
        }
    }
}