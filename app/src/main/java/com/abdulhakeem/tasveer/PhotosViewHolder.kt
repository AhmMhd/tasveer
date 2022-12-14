package com.abdulhakeem.tasveer

import androidx.recyclerview.widget.RecyclerView
import com.abdulhakeem.tasveer.data.Media
import com.abdulhakeem.tasveer.databinding.ItemPhotoBinding

class PhotosViewHolder(private val binding: ItemPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Media) {
        binding.item = item
        binding.executePendingBindings()
    }
}