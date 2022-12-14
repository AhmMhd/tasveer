package com.abdulhakeem.tasveer.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulhakeem.tasveer.data.model.Album
import com.abdulhakeem.tasveer.domain.FetchAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val fetchAlbumUseCase: FetchAlbumUseCase) :
    ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
        }

    init {
        fetchAlbums()
    }

    fun fetchAlbums() {
        viewModelScope.launch(coroutineExceptionHandler) {
            fetchAlbumUseCase().let {
                _albums.value = it
            }
        }
    }

}