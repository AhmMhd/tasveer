package com.abdulhakeem.tasveer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulhakeem.tasveer.data.Media
import com.abdulhakeem.tasveer.data.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val repository: PhotoRepository) : ViewModel() {

    private val _photos = MutableLiveData<List<Media>>()
    val photos: LiveData<List<Media>> = _photos
    var albumName = ""
    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
        }

    fun handleArgs(args: PhotosFragmentArgs) {
        albumName = args.album.albumName
        fetchPhotos(args.album.albumName)
    }

    private fun fetchPhotos(albumName: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            _photos.value = repository.fetchMediaByAlbumName(albumName)
        }
    }
}