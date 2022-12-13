package com.abdulhakeem.tasveer.data

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface LocalPhotoDataSource {
    suspend fun fetchAlbums():List<Album>
}

class LocalPhotoDataSourceImpl @Inject constructor(private val multiMediaContentResolver: MultiMediaContentResolver) :
    LocalPhotoDataSource {

    override suspend fun fetchAlbums() = multiMediaContentResolver.fetchAlbums()

}