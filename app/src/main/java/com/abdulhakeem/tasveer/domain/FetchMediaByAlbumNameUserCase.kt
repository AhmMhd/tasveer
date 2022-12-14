package com.abdulhakeem.tasveer.domain

import com.abdulhakeem.tasveer.data.model.Media

interface FetchMediaByAlbumNameUserCase {
    suspend operator fun invoke(albumName: String): List<Media>
}