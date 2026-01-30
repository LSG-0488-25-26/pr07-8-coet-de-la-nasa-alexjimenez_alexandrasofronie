package com.example.pr06_lazycomponents.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr06_lazycomponents.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarView(
    viewModel: SearchViewModel = viewModel(),
    onSearch: (String) -> Unit
) {
    val searchedText by viewModel.searchedText.observeAsState("")

    SearchBar(
        query = searchedText,
        onQueryChange = { viewModel.onSearchTextChange(it) },
        onSearch = { onSearch(it) },
        active = false,
        onActiveChange = { },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
        placeholder = { Text("Buscar...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
    }
}
