package com.abdulhakeem.tasveer.domain

import com.abdulhakeem.tasveer.data.model.Album

interface FetchAlbumUseCase{
    suspend operator fun invoke(): List<Album>
}