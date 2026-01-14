package com.example.pr06_lazycomponents.model

import androidx.annotation.DrawableRes

data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    @DrawableRes val image: Int
)