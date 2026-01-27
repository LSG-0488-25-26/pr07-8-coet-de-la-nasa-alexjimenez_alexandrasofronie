package com.example.pr06_lazycomponents.model

data class TMDB_Response_Movies(
    val page: Int,
    val results: List<Result_Movies>,
    val total_pages: Int,
    val total_results: Int
)