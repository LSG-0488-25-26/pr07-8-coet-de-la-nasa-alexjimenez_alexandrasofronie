package com.example.pr06_lazycomponents.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchBarViewModel : ViewModel() {
    //Texto en la search bar
    private val _searchedText = MutableLiveData("")
    val searchedText: LiveData<String> = _searchedText

    //Historial
    private val _searchHistory = MutableLiveData<List<String>>(emptyList())
    val searchHistory: LiveData<List<String>> = _searchHistory

    //Callback para cuando se realiza una búsqueda
    var onSearch: ((String) -> Unit)? = null

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

            // Ejecutar callback
            onSearch?.invoke(text)
        }
    }

    fun clearHistory() {
        _searchHistory.value = emptyList()
    }

    fun clearSearchText() {
        _searchedText.value = ""
    }
}