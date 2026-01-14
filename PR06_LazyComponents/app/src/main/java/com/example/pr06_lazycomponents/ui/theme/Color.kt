package com.example.pr06_lazycomponents.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

//función para añadir un borde de color a cad card en funcion de su tipo
fun getTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "grass" -> Color(0xFF78C850)      // Verde
        "fire" -> Color(0xFFF08030)       // Rojo/Naranja
        "water" -> Color(0xFF6890F0)      // Azul
        "electric" -> Color(0xFFF8D030)   // Amarillo
        "poison" -> Color(0xFFA040A0)     // Morado
        "normal" -> Color(0xFFA8A878)     // Beige
        "fairy" -> Color(0xFFEE99AC)      // Rosa
        "rock" -> Color(0xFFB8A038)       // Marrón
        "flying" -> Color(0xFFA890F0)     // Azul claro
        "fighting" -> Color(0xFFC03028)   // Rojo oscuro
        "psychic" -> Color(0xFFF85888)    // Rosa fuerte
        "bug" -> Color(0xFFA8B820)        // Verde lima
        "ghost" -> Color(0xFF705898)      // Morado oscuro
        "steel" -> Color(0xFFB8B8D0)      // Gris
        "ice" -> Color(0xFF98D8D8)        // Celeste
        "dragon" -> Color(0xFF7038F8)     // Morado intenso
        "dark" -> Color(0xFF705848)       // Marrón oscuro
        "ground" -> Color(0xFFE0C068)     // Amarillo tierra
        else -> Color.Gray                       // Color por defecto
    }
}