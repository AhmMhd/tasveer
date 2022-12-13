package com.abdulhakeem.tasveer.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PhotoRepository {
    suspend fun fetchAlbums():List<Album>
}

class PhotoRepositoryImpl @Inject constructor(private val localSource: LocalPhotoDataSource) :
    PhotoRepository {
    override suspend fun fetchAlbums() = withContext(Dispatchers.IO) { localSource.fetchAlbums() }
}