package com.abdulhakeem.tasveer.data.repository.photos

import com.abdulhakeem.tasveer.data.model.MediaMetaData
import com.abdulhakeem.tasveer.data.MultiMediaContentResolver
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


interface LocalPhotoDataSource {
    fun fetchExternalStorageMediaContents(): List<MediaMetaData>
}

@ViewModelScoped
class LocalPhotoDataSourceImpl @Inject constructor(private val multiMediaContentResolver: MultiMediaContentResolver) :
    LocalPhotoDataSource {
    override fun fetchExternalStorageMediaContents(): List<MediaMetaData> =
        multiMediaContentResolver.fetchExternalStorageMediaContents()

}