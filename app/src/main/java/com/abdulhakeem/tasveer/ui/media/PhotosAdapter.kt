package com.abdulhakeem.tasveer.ui.media

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abdulhakeem.tasveer.ui.common.BaseAdapter
import com.abdulhakeem.tasveer.data.model.Media
import com.abdulhakeem.tasveer.databinding.ItemPhotoBinding

class PhotosAdapter : BaseAdapter<Media, PhotosViewHolder>() {

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