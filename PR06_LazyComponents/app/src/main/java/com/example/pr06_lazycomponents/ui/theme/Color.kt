package com.example.pr06_lazycomponents.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

//función para añadir un borde de color a cada card en función de su género
fun getGenreColor(genre: String): Color {
    return when (genre.lowercase()) {
        "acción" -> Color(0xFFFF5722)          // Rojo/Naranja intenso
        "drama" -> Color(0xFF9C27B0)           // Morado
        "comedia" -> Color(0xFFFFEB3B)         // Amarillo
        "terror" -> Color(0xFF212121)          // Negro/Gris oscuro
        "ciencia ficción" -> Color(0xFF2196F3) // Azul
        "fantasía" -> Color(0xFF9C27B0)        // Morado
        "romance" -> Color(0xFFE91E63)         // Rosa
        "thriller" -> Color(0xFF607D8B)        // Gris azulado
        "aventura" -> Color(0xFF4CAF50)        // Verde
        "animación" -> Color(0xFFFF9800)       // Naranja
        "documental" -> Color(0xFF795548)      // Marrón
        "crimen" -> Color(0xFF3F51B5)          // Azul índigo
        "misterio" -> Color(0xFF673AB7)        // Morado oscuro
        "musical" -> Color(0xFFFFC107)         // Ámbar
        "western" -> Color(0xFF8D6E63)         // Marrón claro
        "guerra" -> Color(0xFF455A64)          // Gris
        "histórica" -> Color(0xFF6D4C41)       // Marrón tierra
        else -> Color.Gray                     // Color por defecto
    }
}