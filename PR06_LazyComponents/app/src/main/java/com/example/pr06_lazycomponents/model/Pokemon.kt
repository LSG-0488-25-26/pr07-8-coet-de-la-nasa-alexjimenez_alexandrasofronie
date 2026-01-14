package com.example.pr06_lazycomponents.model

import androidx.annotation.DrawableRes

data class Pokemon(
    var name: String,
    var type: String,
    @DrawableRes var image: Int
)