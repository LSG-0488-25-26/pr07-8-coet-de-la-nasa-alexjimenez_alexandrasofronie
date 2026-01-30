package com.example.pr06_lazycomponents.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    }
}
