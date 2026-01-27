package com.example.pr06_lazycomponents.model

data class TMD_Response_Series(
    val page: Int,
    val results: List<Result_Series>,
    val total_pages: Int,
    val total_results: Int
)