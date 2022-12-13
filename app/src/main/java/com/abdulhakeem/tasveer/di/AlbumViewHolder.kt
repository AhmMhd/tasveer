package com.abdulhakeem.tasveer.di

import androidx.recyclerview.widget.RecyclerView
import com.abdulhakeem.tasveer.AdapterClickListener
import com.abdulhakeem.tasveer.data.Album
import com.abdulhakeem.tasveer.databinding.ItemAlbumBinding

class AlbumViewHolder(
    private val listener: AdapterClickListener<Album>,
    private val binding: ItemAlbumBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Album) {
        binding.item = item
        binding.listener = listener
        binding.executePendingBindings()
    }
}