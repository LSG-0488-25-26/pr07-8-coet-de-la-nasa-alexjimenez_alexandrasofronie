package com.example.pr06_lazycomponents

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
        Screen.ListScreen.route, 
        Icons.Filled.Home, 
        "Inicio"
    )
    
    object Favorite : BottomNavigationScreens(
        Screen.FavoriteScreen.route, 
        Icons.Filled.Favorite, 
        "Favoritos"
    )
}
