package com.example.pr06_lazycomponents.model

data class MovieDetailsResponse(
    val id: Int,
    val runtime: Int?,
    val credits: CreditsResponse?
)

data class SeriesDetailsResponse(
    val id: Int,
    val episode_run_time: List<Int>?,
    val number_of_seasons: Int?,
    val number_of_episodes: Int?,
    val created_by: List<Creator>?,
    val credits: CreditsResponse?
)

data class CreditsResponse(
    val cast: List<CastMember>?,
    val crew: List<CrewMember>?
)

data class CastMember(
    val name: String
)

data class CrewMember(
    val name: String,
    val job: String
)

data class Creator(
    val name: String
)