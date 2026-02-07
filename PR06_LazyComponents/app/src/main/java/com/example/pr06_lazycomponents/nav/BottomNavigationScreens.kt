package com.example.pr06_lazycomponents.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomNavigationScreens(
        Screen.ListScreen.route,            //La ruta va a MediaListScreen
        Icons.Filled.Home, 
        "Inicio"
    )
    
    object Favorite : BottomNavigationScreens(
        Screen.FavoriteScreen.route,       //La ruta va a FavoritesScreen  
        Icons.Filled.Favorite, 
        "Favoritos"
    )
}
