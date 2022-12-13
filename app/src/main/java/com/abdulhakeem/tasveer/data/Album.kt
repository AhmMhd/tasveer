package com.abdulhakeem.tasveer.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val thumbnail: String,
    val albumName: String
) : Parcelable