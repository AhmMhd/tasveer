package com.abdulhakeem.tasveer

import androidx.recyclerview.widget.RecyclerView
import com.abdulhakeem.tasveer.data.Photo
import com.abdulhakeem.tasveer.databinding.ItemPhotoBinding

class PhotosViewHolder(private val binding: ItemPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Photo) {
        binding.item = item
        binding.executePendingBindings()
    }
}