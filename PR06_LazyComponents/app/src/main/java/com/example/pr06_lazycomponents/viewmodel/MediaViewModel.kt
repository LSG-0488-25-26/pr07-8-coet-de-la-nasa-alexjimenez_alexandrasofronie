package com.example.pr06_lazycomponents.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pr06_lazycomponents.model.Media
import com.example.pr06_lazycomponents.repository.MediaRepository
import kotlinx.coroutines.launch

class MediaViewModel : ViewModel() {
    private val repository = MediaRepository()

    // Para guardar la lista de películas/series
    private val _mediaList = MutableLiveData<List<Media>>()
    val mediaList: LiveData<List<Media>> get() = _mediaList

    // Para guardar la película/serie seleccionada
    private val _selectedMedia = MutableLiveData<Media>()
    val selectedMedia: LiveData<Media> get() = _selectedMedia

    // Estado de carga añadido
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        loadMedia()
    }

    /*
    Carga la lista de películas y series desde la API usando corrutines.
    Ejecuta la llamada en viewModelScope para que se cancele automáticamente
    si el ViewModel deja de usarse.
    */
    private fun loadMedia() {
        viewModelScope.launch {
            _isLoading.value = true
            _mediaList.value = repository.getMediaList()
            _isLoading.value = false
        }
    }

    fun selectMedia(media: Media) {
        _selectedMedia.value = media
    }
}
