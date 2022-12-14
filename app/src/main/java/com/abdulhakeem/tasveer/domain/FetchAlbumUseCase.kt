package com.abdulhakeem.tasveer.domain

import com.abdulhakeem.tasveer.data.model.Album

interface FetchAlbumUseCase{
    operator suspend fun invoke(): List<Album>
}