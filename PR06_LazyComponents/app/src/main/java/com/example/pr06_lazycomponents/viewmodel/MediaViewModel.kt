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

    private val _allMediaList = MutableLiveData<List<Media>>(emptyList())

    // Para guardar la lista de películas/series
    private val _mediaList = MutableLiveData<List<Media>>()
    val mediaList: LiveData<List<Media>> get() = _mediaList

    // Para guardar la película/serie seleccionada
    private val _selectedMedia = MutableLiveData<Media>()
    val selectedMedia: LiveData<Media> get() = _selectedMedia

    // Estado de carga añadido
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isLoadingMore = MutableLiveData<Boolean>(false)
    val isLoadingMore: LiveData<Boolean> get() = _isLoadingMore

    // Estado de búsqueda
    private val _isSearching = MutableLiveData(false)
    val isSearching: LiveData<Boolean> get() = _isSearching

    private val _currentSearchQuery = MutableLiveData<String>("")
    val currentSearchQuery: LiveData<String> get() = _currentSearchQuery

    private val _isSearchBarVisible = MutableLiveData(false)
    val isSearchBarVisible: LiveData<Boolean> get() = _isSearchBarVisible

    private val _canLoadMore = MutableLiveData<Boolean>(true)
    val canLoadMore: LiveData<Boolean> get() = _canLoadMore

    private val _favoritesList = MutableLiveData<List<Media>>(emptyList())
    val favoritesList: LiveData<List<Media>> get() = _favoritesList

    private val _favoritesIds = MutableLiveData<Set<Int>>(emptySet())
    val favoritesIds: LiveData<Set<Int>> get() = _favoritesIds

    init {
        loadInitialMedia()
    }

    /*
    Carga la lista de películas y series desde la API usando corrutines.
    Ejecuta la llamada en viewModelScope para que se cancele automáticamente
    si el ViewModel deja de usarse.
    */
    private fun loadInitialMedia() {
        viewModelScope.launch {
            _isLoading.value = true
            val media = repository.getMediaList()
            _allMediaList.value = media
            _mediaList.value = media
            _isLoading.value = false
        }
    }

    fun loadMoreMedia() {
        if (_isLoadingMore.value == true || _canLoadMore.value == false) {
            return
        }

        viewModelScope.launch {
            _isLoadingMore.value = true
            val moreMedia = repository.loadMoreMedia()

            if (moreMedia.isNotEmpty()) {
                val currentAllMedia = _allMediaList.value ?: emptyList()
                val currentMedia = _mediaList.value ?: emptyList()

                _allMediaList.value = currentAllMedia + moreMedia
                _mediaList.value = currentMedia + moreMedia
            } else {
                // No hay más contenido
                _canLoadMore.value = false
            }

            _isLoadingMore.value = false
        }
    }

    // Función para buscar en la lista
    fun searchMedia(query: String) {
        _currentSearchQuery.value = query
        _isSearching.value = true

        val allMedia = _allMediaList.value ?: emptyList()

        if (query.isEmpty()) {
            _mediaList.value = allMedia
            _isSearching.value = false
            return
        }

        val lowerCaseQuery = query.lowercase()
        val filteredList = allMedia.filter { media ->
            media.title.lowercase().contains(lowerCaseQuery) ||
            media.genre.lowercase().contains(lowerCaseQuery) ||
            media.description.lowercase().contains(lowerCaseQuery) ||
            media.year.toString().contains(lowerCaseQuery)
        }

        _mediaList.value = filteredList
        _isSearching.value = false
    }

    fun clearSearch() {
        _currentSearchQuery.value = ""
        _mediaList.value = _allMediaList.value
        _canLoadMore.value = true
        repository.resetPagination()
    }

    fun selectMedia(media: Media) {
        _selectedMedia.value = media
    }

    // Funciones para controlar la visibilidad de la SearchBar
    fun toggleSearchBarVisibility() {
        _isSearchBarVisible.value = !(_isSearchBarVisible.value ?: false)
    }

    fun setSearchBarVisibility(visible: Boolean) {
        _isSearchBarVisible.value = visible
    }

    fun toggleFavorite(media: Media) {
        val currentIds = _favoritesIds.value ?: emptySet()
        val currentFavorites = _favoritesList.value ?: emptyList()

        if (media.id in currentIds) {
            // Remover de favoritos
            _favoritesIds.value = currentIds - media.id
            _favoritesList.value = currentFavorites.filter { it.id != media.id }
        } else {
            // Agregar a favoritos
            _favoritesIds.value = currentIds + media.id
            _favoritesList.value = currentFavorites + media
        }
    }

    // Verificar si un media es favorito
    fun isFavorite(mediaId: Int): Boolean {
        return _favoritesIds.value?.contains(mediaId) ?: false
    }

}
