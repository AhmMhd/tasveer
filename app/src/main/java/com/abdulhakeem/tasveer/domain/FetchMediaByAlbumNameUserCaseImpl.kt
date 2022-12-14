package com.abdulhakeem.tasveer.domain

import com.abdulhakeem.tasveer.data.model.Media
import com.abdulhakeem.tasveer.data.model.MediaType
import com.abdulhakeem.tasveer.data.repository.photos.PhotoRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class FetchMediaByAlbumNameUserCaseImpl @Inject constructor(
    private val photoRepository: PhotoRepository
) : FetchMediaByAlbumNameUserCase {

    override suspend fun invoke(albumName: String): List<Media> = withContext(Dispatchers.IO) {
        photoRepository.fetchExternalStorageMediaContents().filter {
            it.folderName == albumName
        }.map {
            Media(
                thumbnail = it.path,
                albumName = it.folderName,
                photoName = it.name,
                mediaType = if (it.contentType.contains("video")) MediaType.Video else MediaType.Image
            )
        }
    }
}