package com.example.pr06_lazycomponents.model

import androidx.annotation.DrawableRes

// Enum para distinguir entre película y serie
enum class MediaType {
    MOVIE,          // Peliculas
    SERIES          // Series
}

// Data class principal para películas y series
data class Media(
    val title: String,                  // itulo
    val mediaType: MediaType,           // Tipo Movie o Series
    val genre: String,                  // Acción, Drama, Comedia
    @DrawableRes val image: Int,        // Imagen de la portada
    val year: Int,                      // Año de lanzamiento
    val rating: Double,                 // Puntuación del 0 al 10
    val description: String,            // Sinopsis
    val details: MediaDetails           // Detalles adicionales
)

// Detalles adicionales de cada película/serie
data class MediaDetails(
    val duration: String,           // "2h 30min" para películas o "45min/episodio" para series
    val director: String,           // Director
    val cast: List<String>,         // Lista de actores principales
    val seasons: Int? = null,       // Solo para series
    val episodes: Int? = null       // Solo para series
)
