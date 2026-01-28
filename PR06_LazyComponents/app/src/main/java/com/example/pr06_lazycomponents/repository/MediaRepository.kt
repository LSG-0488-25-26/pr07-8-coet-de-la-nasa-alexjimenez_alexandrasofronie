package com.example.pr06_lazycomponents.repository

import android.util.Log
import com.example.pr06_lazycomponents.model.Media
import com.example.pr06_lazycomponents.model.toMedia
import com.example.pr06_lazycomponents.model.MediaType
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
                moviesResponse.body()?.results?.let { movies ->
                    mediaList.addAll(movies.map { it.toMedia() })
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
                seriesResponse.body()?.results?.let { series ->
                    mediaList.addAll(series.map { it.toMedia() })
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
}