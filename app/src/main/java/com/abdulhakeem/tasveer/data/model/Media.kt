package com.abdulhakeem.tasveer.data.model

data class Media(
    val thumbnail: String,
    val albumName: String,
    val photoName: String,
    val mediaType: MediaType
)

enum class MediaType{
    Video,
    Image
}