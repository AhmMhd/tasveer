package com.abdulhakeem.tasveer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val thumbnail: String,
    val albumName: String,
    val albumType: AlbumType = AlbumType.Mix
) : Parcelable

enum class AlbumType {
    Image,
    Video,
    Mix
}