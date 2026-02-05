package com.example.pr06_lazycomponents.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pr06_lazycomponents.model.Media
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr06_lazycomponents.view.components.MediaItem
import com.example.pr06_lazycomponents.view.components.SearchBarView
import com.example.pr06_lazycomponents.view.components.MyTopAppBar
import com.example.pr06_lazycomponents.viewmodel.MediaViewModel
import com.example.pr06_lazycomponents.viewmodel.SearchBarViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MediaListScreen(
    onMediaClick: (Media) -> Unit,
    viewModel: MediaViewModel
) {
    val mediaList by viewModel.mediaList.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val isSearching by viewModel.isSearching.observeAsState(initial = false)
    val currentSearchQuery by viewModel.currentSearchQuery.observeAsState(initial = "")

    val searchBarViewModel: SearchBarViewModel = viewModel()

    //Estado para mostrar/ocultar la SearchBar
    var isSearchBarVisible by remember {
        mutableStateOf(false)
    }
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //Usamos el component MyTopAppBar
        MyTopAppBar(
            title = if (currentSearchQuery.isNotEmpty()) {
                "🔍 Resultados para: \"$currentSearchQuery\""
            } else {
                "🎬 Películas & Series"
            },
            onSearchClick = {
                isSearchBarVisible = !isSearchBarVisible
            }
        )

        Spacer(modifier = Modifier.height(16.dp))  // Espacio después del TopAppBar

        if (isSearchBarVisible) {
            SearchBarView(
                myViewModel = searchBarViewModel,
                onSearch = { query ->
                    viewModel.searchMedia(query)
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        if (isSearching) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Buscando...",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        } else {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                mediaList.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = if (currentSearchQuery.isNotEmpty()) {
                                    "No se encontraron resultados para \"$currentSearchQuery\""
                                } else {
                                    "No hay contenido disponible"
                                },
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            if (currentSearchQuery.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Intenta con otras palabras o limpia la búsqueda",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                else -> {
                    if (currentSearchQuery.isNotEmpty()) {
                        Text(
                            text = "Encontrados: ${mediaList.size} resultado${if (mediaList.size != 1) "s" else ""}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 8.dp)
                        )
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(11.dp),
                        contentPadding = PaddingValues(bottom = 20.dp)

                    ) {
                        items(mediaList) { media ->
                            MediaItem(
                                media = media,
                                onClick = { onMediaClick(media) }
                            )
                        }
                    }
                }
            }
        }
    }
}
