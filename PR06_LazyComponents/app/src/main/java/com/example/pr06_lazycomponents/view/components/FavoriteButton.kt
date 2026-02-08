package com.example.pr06_lazycomponents.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pr06_lazycomponents.model.Media
import com.example.pr06_lazycomponents.viewmodel.MediaViewModel

@Composable
fun FavoriteButton(
    media: Media,
    modifier: Modifier = Modifier,
    viewModel: MediaViewModel
) {
    val favoritesIds by viewModel.favoritesIds.observeAsState(initial = emptySet())
    val isFavorite = media.id in favoritesIds

    Icon(
        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
        contentDescription = if (isFavorite) "Quitar de favoritos" else "Agregar a favoritos",
        tint = if (isFavorite) Color.Red else Color.Gray,
        modifier = modifier
            .size(32.dp)
            .clickable(
                onClick = {
                    viewModel.toggleFavorite(media)
                },
            )
    )
}