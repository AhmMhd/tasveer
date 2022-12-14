package com.abdulhakeem.tasveer.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abdulhakeem.tasveer.ui.common.AdapterClickListener
import com.abdulhakeem.tasveer.ui.common.BaseAdapter
import com.abdulhakeem.tasveer.data.model.Album
import com.abdulhakeem.tasveer.databinding.ItemAlbumBinding

class AlbumAdapter(private val listener: AdapterClickListener<Album>) :
    BaseAdapter<Album, AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AlbumViewHolder(
            listener,
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) =
        holder.bind(data[position])

}