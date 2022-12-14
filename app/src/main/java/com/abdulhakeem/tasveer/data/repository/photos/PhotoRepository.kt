package com.abdulhakeem.tasveer.data.repository.photos

import com.abdulhakeem.tasveer.data.model.MediaMetaData
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface PhotoRepository {
    fun fetchExternalStorageMediaContents(): List<MediaMetaData>

}

@ViewModelScoped
class PhotoRepositoryImpl @Inject constructor(private val localSource: LocalPhotoDataSource) :
    PhotoRepository {
    override fun fetchExternalStorageMediaContents(): List<MediaMetaData> =
        localSource.fetchExternalStorageMediaContents()

}