package com.example.pr06_lazycomponents.view.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pr06_lazycomponents.nav.BottomNavigationScreens
import com.example.pr06_lazycomponents.nav.Screen

@Composable
fun MyBottomBar(navController: NavHostController) {
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Favorite
    )

    NavigationBar(
        containerColor = Color.LightGray,
        contentColor = Color.Black,
        modifier = Modifier.height(110.dp)
    ) {
        val navBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackEntry?.destination?.route

        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    } else if (item.route == Screen.ListScreen.route) {
                        // Si ya estás en Home, navegas de nuevo para resetear el estado
                        navController.navigate(item.route) {
                            popUpTo(Screen.ListScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.DarkGray,
                    unselectedTextColor = Color.DarkGray,
                    indicatorColor = Color.LightGray
                )
            )
        }
    }
}