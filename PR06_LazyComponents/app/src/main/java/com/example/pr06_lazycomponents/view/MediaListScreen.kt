package com.example.pr06_lazycomponents.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pr06_lazycomponents.model.Media
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr06_lazycomponents.view.components.MediaItem
import com.example.pr06_lazycomponents.view.components.SearchBarView
import com.example.pr06_lazycomponents.viewmodel.MediaViewModel
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MediaListScreen(
    onMediaClick: (Media) -> Unit,
    isSearchBarVisible: Boolean = false,
    viewModel: MediaViewModel
) {
    val mediaList by viewModel.mediaList.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val isLoadingMore by viewModel.isLoadingMore.observeAsState(initial = false)
    val isSearching by viewModel.isSearching.observeAsState(initial = false)
    val currentSearchQuery by viewModel.currentSearchQuery.observeAsState(initial = "")
    val canLoadMore by viewModel.canLoadMore.observeAsState(initial = true)

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                if (lastVisibleItem != null && lastVisibleItem.index >= layoutInfo.totalItemsCount - 5) {
                    // Si estamos cerca del final y no estamos buscando, cargar más
                    if (!isSearching && currentSearchQuery.isEmpty() && canLoadMore && !isLoadingMore) {
                        viewModel.loadMoreMedia()
                    }
                }
            }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isSearchBarVisible) {
            SearchBarView(
                myViewModel = viewModel(),
                onSearch = { query ->
                    viewModel.searchMedia(query)
                },
                onClear = {
                    viewModel.clearSearch()
                }
            )
        }

        when{
            isSearching -> {
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
            }
            isLoading && mediaList.isEmpty() -> {
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
                                fontSize = 16.sp,
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
                        text = "Encontrados: ${mediaList.size} resultado${
                            if (mediaList.size != 1) "s" else ""
                        }",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 8.dp)
                    )
                } else if (mediaList.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))  // Spacer entre la toppbar y el texto
                    Text(
                        text = "Desplázate para cargar más contenido",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 4.dp)
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(11.dp),
                    contentPadding = PaddingValues(
                        top = 7.dp,
                        bottom = 60.dp
                    ),
                    state = listState
                ) {
                    items(mediaList) { media ->
                        MediaItem(
                            media = media,
                            onClick = { onMediaClick(media) }
                        )
                    }

                    if (isLoadingMore && canLoadMore && currentSearchQuery.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    CircularProgressIndicator(modifier = Modifier.size(36.dp))
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Cargando más contenido...",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }

                        // Mostrar mensaje si no hay más contenido
                    if (!canLoadMore && currentSearchQuery.isEmpty() && mediaList.isNotEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "¡Has llegado al final!",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
