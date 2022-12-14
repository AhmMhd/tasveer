package com.abdulhakeem.tasveer.ui.media

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulhakeem.tasveer.PhotosFragmentArgs
import com.abdulhakeem.tasveer.data.model.AlbumType
import com.abdulhakeem.tasveer.data.model.Media
import com.abdulhakeem.tasveer.domain.FetchMediaByAlbumNameUserCase
import com.abdulhakeem.tasveer.domain.FetchMediaByMediaTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val fetchMediaByMediaTypeUseCase: FetchMediaByMediaTypeUseCase,
    private val fetchMediaByAlbumNameUserCase: FetchMediaByAlbumNameUserCase
) : ViewModel() {

    private val _photos = MutableLiveData<List<Media>>()
    val photos: LiveData<List<Media>> = _photos
    var albumName = ""
    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
        }

    fun handleArgs(args: PhotosFragmentArgs) {
        albumName = args.album.albumName

        when (args.album.albumType) {
            AlbumType.Mix -> fetchMediaByAlbumName(args.album.albumName)
            else -> fetchMediaByAlbumType(args.album.albumType)
        }
    }

    private fun fetchMediaByAlbumName(albumName: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            fetchMediaByAlbumNameUserCase(albumName).let {
                _photos.value = it
            }
        }
    }


    private fun fetchMediaByAlbumType(albumType: AlbumType) {
        viewModelScope.launch(coroutineExceptionHandler) {
            fetchMediaByMediaTypeUseCase(albumType).let {
                _photos.value = it
            }
        }
    }

}