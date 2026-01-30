package com.example.pr06_lazycomponents.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val searchHistory by viewModel.searchHistory.observeAsState(emptyList())

    SearchBar(
        query = searchedText,
        onQueryChange = { viewModel.onSearchTextChange(it) },
        onSearch = {
            viewModel.addToHistory(it)
            onSearch(it)
        },
        active = false,
        onActiveChange = { },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            if (searchedText.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear",
                    tint = Color.Red,
                    modifier = Modifier.clickable {
                        viewModel.clearSearchText()
                    }
                )
            }
        },
        placeholder = { Text("Buscar...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (searchHistory.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(searchHistory) { search ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Text(
                            text = search,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.onSearchTextChange(search)
                                    onSearch(search)
                                }
                                .padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
