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
                albums.add(Album(thumbnail = it.path, albumName = "All Images", AlbumType.Image))
            }

            data.firstOrNull {
                it.contentType.contains("video")
            }?.let {
                albums.add(Album(thumbnail = it.path, albumName = "All Videos", AlbumType.Video))
            }

            data.distinctBy {
                it.folderName
            }.map {
                val albumType = when (it.folderName.lowercase()) {
                    "all images" -> AlbumType.Image
                    "all videos" -> AlbumType.Image
                    else -> AlbumType.Mix
                }
                Album(thumbnail = it.path, albumName = it.folderName, albumType)
            }.let {
                albums.addAll(it)
            }

            albums
        }

    }

}