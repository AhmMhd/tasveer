package com.abdulhakeem.tasveer.data

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