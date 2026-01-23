package com.example.pr06_lazycomponents.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pr06_lazycomponents.model.Media
import com.example.pr06_lazycomponents.repository.MediaRepository

class MediaViewModel : ViewModel() {
    private val repository = MediaRepository()

    // Para guardar la lista de películas/series
    private val _mediaList = MutableLiveData<List<Media>>()
    val mediaList: LiveData<List<Media>> get() = _mediaList

    // Para guardar la película/serie seleccionada
    private val _selectedMedia = MutableLiveData<Media>()
    val selectedMedia: LiveData<Media> get() = _selectedMedia

    init {
        loadMedia()
    }

    private fun loadMedia() {
        _mediaList.value = repository.getMediaList()
    }

    fun selectMedia(media: Media) {
        _selectedMedia.value = media
    }
}
