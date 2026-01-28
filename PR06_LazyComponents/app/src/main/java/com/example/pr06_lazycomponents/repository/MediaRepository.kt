package com.example.pr06_lazycomponents.repository

import android.util.Log
import com.example.pr06_lazycomponents.model.Media
import com.example.pr06_lazycomponents.model.MediaDetails
import com.example.pr06_lazycomponents.model.toMedia
import com.example.pr06_lazycomponents.network.TMDBApiService

class MediaRepository {
    private val apiService = TMDBApiService.create()            
    private val apiKey = "2eeb8acfafa6759fbf6a31759683f0b2"     //API KEY de TMDB
    
    /*
        Obtenemos la lista combinada de películas y series desde la API de TMDB.
        Convertimos las respuestas JSON a objetos Media usando las funciones de extensión.
        Indicamos con un return Lista de Media (películas y series) o lista vacía
        si hay un error
    */
    
    suspend fun getMediaList(): List<Media> {       //suspend fun para usar las corrutines
        return try {
            val mediaList = mutableListOf<Media>()
            
            // Obtenemos las películas populares
            val moviesResponse = apiService.getPopularMovies(apiKey, "es-ES")
            
            if (moviesResponse.isSuccessful) {      //Comprobamos si la petición de la APi fue correcta
                val moviesBody = moviesResponse.body()
                if (moviesBody != null) {
                    val movies = moviesBody.results
                    for (movie in movies.take(15)) {
                        val details = getMovieDetails(movie.id)
                        mediaList.add(movie.toMedia(details))
                    }
                    /*
                    Convierte cada Result_Movies a Media usando
                    la función de extensión toMedia() creada en Media.kt
                    */
                    Log.d("MediaRepository", "Películas cargadas: ${movies.size}")
                }
            } else {
                Log.e("MediaRepository", "Error al cargar películas: ${moviesResponse.code()}")
            }
            
            // Obtenemos las series populares
            val seriesResponse = apiService.getPopularSeries(apiKey, "es-ES")
            
            if (seriesResponse.isSuccessful) {
                val seriesBody = seriesResponse.body()
                if (seriesBody != null) {
                    val series = seriesBody.results
                    for (serie in series.take(15)) {
                        val details = getSeriesDetails(serie.id)
                        mediaList.add(serie.toMedia(details))
                    }
                    Log.d("MediaRepository", "Series cargadas: ${series.size}")
                }
            } else {
                Log.e("MediaRepository", "Error al cargar series: ${seriesResponse.code()}")
            }
            
            Log.d("MediaRepository", "Total items cargados: ${mediaList.size}")
            mediaList
            
        } catch (e: Exception) {
            Log.e("MediaRepository", "Excepción al cargar datos: ${e.message}")
            emptyList()
        }
    }

    private suspend fun getMovieDetails(movieId: Int): MediaDetails? {
        return try {
            val response = apiService.getMovieDetails(movieId, apiKey, "es-ES")
            if (response.isSuccessful) {
                val movieDetails = response.body()
                if (movieDetails != null) {
                    // Obtener director
                    var director = "Director no disponible"
                    if (movieDetails.credits != null && movieDetails.credits.crew != null) {
                        val directorFound = movieDetails.credits.crew.find { it.job == "Director" }
                        if (directorFound != null) {
                            director = directorFound.name
                        }
                    }

                    // Obtener actores
                    val cast = mutableListOf<String>()
                    if (movieDetails.credits != null && movieDetails.credits.cast != null) {
                        val castMembers = movieDetails.credits.cast.take(5)
                        for (actor in castMembers) {
                            if (actor.name != null) {
                                cast.add(actor.name)
                            }
                        }
                    }

                    // Formatear duración
                    var duration = "Duración no disponible"
                    if (movieDetails.runtime != null && movieDetails.runtime > 0) {
                        val hours = movieDetails.runtime / 60
                        val minutes = movieDetails.runtime % 60
                        if (hours > 0) {
                            duration = "${hours}h ${minutes}min"
                        } else {
                            duration = "${minutes}min"
                        }
                    }

                    MediaDetails(
                        duration = duration,
                        director = director,
                        cast = cast,
                        seasons = null,
                        episodes = null
                    )
                } else {
                    null
                }
            } else {
                Log.e("MediaRepository", "Error detalles película: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("MediaRepository", "Excepción detalles película: ${e.message}")
            null
        }
    }

    private suspend fun getSeriesDetails(seriesId: Int): MediaDetails? {
        return try {
            val response = apiService.getSeriesDetails(seriesId, apiKey, "es-ES")
            if (response.isSuccessful) {
                val seriesDetails = response.body()
                if (seriesDetails != null) {
                    // Obtener creador
                    var director = "Creador no disponible"
                    if (seriesDetails.created_by != null && seriesDetails.created_by.isNotEmpty()) {
                        val creator = seriesDetails.created_by[0]
                        if (creator.name != null) {
                            director = creator.name
                        }
                    }

                    // Obtener actores
                    val cast = mutableListOf<String>()
                    if (seriesDetails.credits != null && seriesDetails.credits.cast != null) {
                        val castMembers = seriesDetails.credits.cast.take(5)
                        for (actor in castMembers) {
                            if (actor.name != null) {
                                cast.add(actor.name)
                            }
                        }
                    }

                    // Formatear duración por episodio
                    var duration = "Duración no disponible"
                    if (seriesDetails.episode_run_time != null && seriesDetails.episode_run_time.isNotEmpty()) {
                        val runtime = seriesDetails.episode_run_time[0]
                        duration = "${runtime} min/episodio"
                    }

                    MediaDetails(
                        duration = duration,
                        director = director,
                        cast = cast,
                        seasons = seriesDetails.number_of_seasons,
                        episodes = seriesDetails.number_of_episodes
                    )
                } else {
                    null
                }
            } else {
                Log.e("MediaRepository", "Error detalles serie: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("MediaRepository", "Excepción detalles serie: ${e.message}")
            null
        }
    }
}