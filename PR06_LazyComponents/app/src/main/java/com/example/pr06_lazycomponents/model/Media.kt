package com.example.pr06_lazycomponents.model

// Enum para distinguir entre película y serie
enum class MediaType {
    MOVIE,          // Peliculas
    SERIES          // Series
}

// Data class principal para películas y series
data class Media(
    val id: Int,                        // id para las peliculas o series
    val title: String,                  // itulo
    val mediaType: MediaType,           // Tipo Movie o Series
    val genre: String,                  // Acción, Drama, Comedia
    val imageUrl: String,               // Imagen de la portada adaptada a la api
    val year: Int,                      // Año de lanzamiento
    val rating: Double,                 // Puntuación del 0 al 10
    val description: String,            // Sinopsis
    val details: MediaDetails? = null   // Detalles adicionales (opcional)
)

// Detalles adicionales de cada película/serie
data class MediaDetails(
    val duration: String,           // "2h 30min" para películas o "45min/episodio" para series
    val director: String,           // Director
    val cast: List<String>,         // Lista de actores principales
    val seasons: Int? = null,       // Solo para series
    val episodes: Int? = null       // Solo para series
)

//object para convertir IDs de géneros a nombres (tipos de genero de la pelicula o serie)
object GenreMapper {
    private val genreMap = mapOf(
        28 to "Acción",
        12 to "Aventura",
        16 to "Animación",
        35 to "Comedia",
        80 to "Crimen",
        99 to "Documental",
        18 to "Drama",
        10751 to "Familia",
        14 to "Fantasía",
        36 to "Historia",
        27 to "Terror",
        10402 to "Música",
        9648 to "Misterio",
        10749 to "Romance",
        878 to "Ciencia Ficción",
        10770 to "Película de TV",
        53 to "Suspense",
        10752 to "Bélica",
        37 to "Western"
    )
    
    fun convertGenreIds(genreIds: List<Int>): String {
        return genreIds.mapNotNull { genreMap[it] }
            .take(2)  // Máximo 2 géneros
            .joinToString(", ")
            .ifEmpty { "Sin género" }
    }
}

//Funciones de extensión para convertir de TMDB a Media
fun Result_Movies.toMedia(details: MediaDetails?): Media {
    val description = if (this.overview.isNullOrBlank()) {
        "Sinopsis no disponible para esta película."
    } else {
        this.overview
    }

    return Media(
        id = this.id,
        title = this.title,
        mediaType = MediaType.MOVIE,
        genre = GenreMapper.convertGenreIds(this.genre_ids),
        imageUrl = "https://image.tmdb.org/t/p/w500${this.poster_path}",
        year = this.release_date.take(4).toIntOrNull() ?: 0,
        rating = (this.vote_average * 10).toInt() / 10.0,   // Redondeamos a 1 decimal
        description = description,
        details = details
    )
}

fun Result_Series.toMedia(details: MediaDetails?): Media {
    // Manejar sinopsis vacías
    val description = if (this.overview.isNullOrBlank()) {
        "Sinopsis no disponible para esta serie."
    } else {
        this.overview
    }
    return Media(
        id = this.id,
        title = this.name,                                      // Las series usan "name" en vez de "title"
        mediaType = MediaType.SERIES,
        genre = GenreMapper.convertGenreIds(this.genre_ids),
        imageUrl = "https://image.tmdb.org/t/p/w500${this.poster_path}",
        year = this.first_air_date.take(4).toIntOrNull() ?: 0,  // "first_air_date" en vez de "release_date"
        rating = (this.vote_average * 10).toInt() / 10.0,
        description = description,
        details = details
    )
}