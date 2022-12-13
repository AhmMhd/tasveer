package com.abdulhakeem.tasveer.data

import javax.inject.Inject


interface LocalPhotoDataSource {
    suspend fun fetchAlbums():List<Album>
    suspend fun fetchMediaByAlbumName(albumName: String): List<Photo>
}

class LocalPhotoDataSourceImpl @Inject constructor(private val multiMediaContentResolver: MultiMediaContentResolver) :
    LocalPhotoDataSource {

    override suspend fun fetchAlbums() = multiMediaContentResolver.fetchAlbums()

    override suspend fun fetchMediaByAlbumName(albumName: String): List<Photo>  = multiMediaContentResolver.fetchAlbumMedia(albumName)

}