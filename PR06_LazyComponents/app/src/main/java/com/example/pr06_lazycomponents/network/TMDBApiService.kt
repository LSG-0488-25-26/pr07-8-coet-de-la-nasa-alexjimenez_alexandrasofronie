package com.example.pr06_lazycomponents.network

import com.example.pr06_lazycomponents.model.MovieDetailsResponse
import com.example.pr06_lazycomponents.model.SeriesDetailsResponse
import com.example.pr06_lazycomponents.model.TMDB_Response_Movies
import com.example.pr06_lazycomponents.model.TMD_Response_Series
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Interfaz de Retrofit implementada para conectar con la API de TMDB
interface TMDBApiService {
    
    // Endpoint GET para obtener las películas populares
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): Response<TMDB_Response_Movies>

    
    // Endpoint GET para obtener las series populares
    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): Response<TMD_Response_Series>

    // Endpoint para obtener detalles completos de una película
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("append_to_response") appendToResponse: String = "credits" // Para obtener el cast y crew
    ): Response<MovieDetailsResponse>

    // Endpoint para obtener detalles completos de una serie
    @GET("tv/{series_id}")
    suspend fun getSeriesDetails(
        @Path("series_id") seriesId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("append_to_response") appendToResponse: String = "credits" // Para obtener el cast y crew
    ): Response<SeriesDetailsResponse>

    
    companion object {
        // URL base de la API TMDB
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        
        /*
            Creamos una instancia de TMDBApiService configurada con Retrofit
            Usamos GsonConverterFactory para convertir los JSON a objetos Kotlin
        */
        fun create(): TMDBApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(TMDBApiService::class.java)
        }
    }
}