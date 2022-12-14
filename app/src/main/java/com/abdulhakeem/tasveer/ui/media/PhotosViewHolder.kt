package com.abdulhakeem.tasveer.ui.media

import androidx.recyclerview.widget.RecyclerView
import com.abdulhakeem.tasveer.data.model.Media
import com.abdulhakeem.tasveer.databinding.ItemPhotoBinding

class PhotosViewHolder(private val binding: ItemPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Media) {
        binding.item = item
        binding.executePendingBindings()
    }
}