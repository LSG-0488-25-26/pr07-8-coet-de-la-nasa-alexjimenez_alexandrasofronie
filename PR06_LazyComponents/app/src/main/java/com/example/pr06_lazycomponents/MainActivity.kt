package com.example.pr06_lazycomponents

import  android.os.Bundle
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
import com.example.pr06_lazycomponents.model.Media
import com.example.pr06_lazycomponents.model.MediaDetails
import com.example.pr06_lazycomponents.model.MediaType
import com.example.pr06_lazycomponents.ui.theme.PR06_LazyComponentsTheme
import com.example.pr06_lazycomponents.view.MediaDetailScreen
import com.example.pr06_lazycomponents.view.MediaListScreen
import com.example.pr06_lazycomponents.view.components.MyBottomBar
import com.example.pr06_lazycomponents.viewmodel.MediaViewModel
import com.example.pr06_lazycomponents.view.FavoriteScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PR06_LazyComponentsTheme {
                val navController = rememberNavController()
                val viewModel: MediaViewModel = viewModel()
                
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { MyBottomBar(navController) }      //BottomBar 
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ListScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.ListScreen.route) {
                            MediaListScreen(
                                viewModel = viewModel,
                                onMediaClick = { media ->
                                    viewModel.selectMedia(media)
                                    navController.navigate(Screen.DetailScreen.route)
                                }
                            )
                        }

                        composable(Screen.DetailScreen.route) {
                            // Obtenemos la película/serie del ViewModel
                            val selectedMedia = viewModel.selectedMedia.value

                            if (selectedMedia != null) {
                                MediaDetailScreen(
                                    media = selectedMedia,
                                    onBackClick = { navController.navigateUp() }
                                )
                            }
                        }

                        composable(Screen.FavoriteScreen.route) {
                            FavoriteScreen()
                        }
                    }
                }
            }
        }
    }
}

//Preview para MediaListScreen
@Preview(showBackground = true, name = "Media List Screen")
@Composable
fun MediaListScreenPreview() {
    PR06_LazyComponentsTheme {
        MediaListScreen(
            viewModel = viewModel(),
            onMediaClick = { }
        )
    }
}

//Preview para MediaDetailScreen
@Preview(showBackground = true, name = "Media Detail Screen")
@Composable
fun MediaDetailScreenPreview() {
    PR06_LazyComponentsTheme {
        MediaDetailScreen(
            media = Media(
                id = 1,
                title = "Inception",
                mediaType = MediaType.MOVIE,
                genre = "Ciencia Ficción",
                imageUrl = "https://image.tmdb.org/t/p/w500/qmDpIHrmpJINaRKAfWQfftjCdyi.jpg",
                year = 2010,
                rating = 8.8,
                description = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños.",
                details = MediaDetails(
                    duration = "2h 28min",
                    director = "Christopher Nolan",
                    cast = listOf("Leonardo DiCaprio", "Joseph Gordon-Levitt")
                )
            ),
            onBackClick = { }
        )
    }
}