package com.abdulhakeem.tasveer.ui.albums

import androidx.recyclerview.widget.RecyclerView
import com.abdulhakeem.tasveer.ui.common.AdapterClickListener
import com.abdulhakeem.tasveer.data.model.Album
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