package com.example.pr06_lazycomponents.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    //Texto en la search bar
    private val _searchedText = MutableLiveData("")
    val searchedText: LiveData<String> = _searchedText

    //Historial
    private val _searchHistory = MutableLiveData<List<String>>(emptyList())
    val searchHistory: LiveData<List<String>> = _searchHistory

    //Estado de búsqueda activa
    private val _isSearchActive = MutableLiveData(false)
    val isSearchActive: LiveData<Boolean> = _isSearchActive

    fun onSearchTextChange(text: String) {
        _searchedText.value = text
    }

    fun addToHistory(text: String) {
        if (text.isNotEmpty()) {
            val currentHistory = _searchHistory.value ?: emptyList()
            // Añadir al inicio y limitar a 10 elementos
            val newHistory = listOf(text) + currentHistory.take(9)
            _searchHistory.value = newHistory
            _searchedText.value = ""
        }
    }

    fun clearHistory() {
        _searchHistory.value = emptyList()
    }

    fun setSearchActive(active: Boolean) {
        _isSearchActive.value = active
    }

    fun clearSearchText() {
        _searchedText.value = ""
    }
}