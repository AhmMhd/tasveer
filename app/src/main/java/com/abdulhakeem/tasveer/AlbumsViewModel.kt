package com.abdulhakeem.tasveer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulhakeem.tasveer.data.Album
import com.abdulhakeem.tasveer.data.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val photoRepository: PhotoRepository) :
    ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    }

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            _albums.value = photoRepository.fetchAlbums()
        }
    }

}