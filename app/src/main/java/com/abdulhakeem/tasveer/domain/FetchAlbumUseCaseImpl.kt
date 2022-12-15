package com.abdulhakeem.tasveer.domain

import com.abdulhakeem.tasveer.data.model.Album
import com.abdulhakeem.tasveer.data.model.AlbumType
import com.abdulhakeem.tasveer.data.repository.photos.PhotoRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class FetchAlbumUseCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
) : FetchAlbumUseCase{

    private val albums = ArrayList<Album>()
    override suspend fun invoke(): List<Album> = withContext(Dispatchers.IO){
        synchronized(albums){

            if (albums.isNotEmpty())
                return@withContext albums

            val data = photoRepository.fetchExternalStorageMediaContents()


            data.firstOrNull {
                it.contentType.contains("video").not()
            }?.let {
                val totalMedia = data.filter { it.contentType.contains("video").not() }.size
                albums.add(Album(thumbnail = it.path, albumName = "All Images", AlbumType.Image,totalMedia))
            }

            data.firstOrNull {
                it.contentType.contains("video")
            }?.let {
                val totalMedia = data.filter { it.contentType.contains("video") }.size
                albums.add(Album(thumbnail = it.path, albumName = "All Videos", AlbumType.Video,totalMedia))
            }

            data.distinctBy {
                it.folderName
            }.map {
                val albumType = when (it.folderName.lowercase()) {
                    "all images" -> AlbumType.Image
                    "all videos" -> AlbumType.Image
                    else -> AlbumType.Mix
                }
                val totalMedia = data.filter {item-> it.folderName == item.folderName }.size
                Album(thumbnail = it.path, albumName = it.folderName, albumType,totalMedia)
            }.let {
                albums.addAll(it)
            }

            albums
        }

    }

}