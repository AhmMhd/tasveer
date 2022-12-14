package com.abdulhakeem.tasveer.domain

import com.abdulhakeem.tasveer.data.model.AlbumType
import com.abdulhakeem.tasveer.data.model.Media

interface FetchMediaByMediaTypeUseCase {
    suspend operator fun invoke(albumtype: AlbumType): List<Media>
}