package com.example.pr06_lazycomponents

sealed class Screen(val route: String) {
    object ListScreen : Screen("list_screen")
    object DetailScreen : Screen("detail_screen")
}