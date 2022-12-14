package com.abdulhakeem.tasveer.domain

import com.abdulhakeem.tasveer.data.model.AlbumType
import com.abdulhakeem.tasveer.data.model.Media
import com.abdulhakeem.tasveer.data.model.MediaType
import com.abdulhakeem.tasveer.data.repository.photos.PhotoRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FetchMediaByMediaTypeUseCaseImpl @Inject constructor(private val photoRepository: PhotoRepository) :
    FetchMediaByMediaTypeUseCase {

    override suspend fun invoke(albumType: AlbumType): List<Media> =
        photoRepository.fetchExternalStorageMediaContents().filter {
            when (albumType) {
                AlbumType.Video -> it.contentType.contains("video")
                else -> it.contentType.contains("video").not()
            }
        }.map {
            Media(
                it.path,
                it.folderName,
                it.name,
                mediaType = if (it.contentType.contains("video")) MediaType.Video else MediaType.Image
            )
        }

}