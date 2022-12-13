package com.abdulhakeem.tasveer

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abdulhakeem.tasveer.data.Photo
import com.abdulhakeem.tasveer.databinding.ItemPhotoBinding

class PhotosAdapter : BaseAdapter<Photo, PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotosViewHolder(
        ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) =
        holder.bind(data[position])
}