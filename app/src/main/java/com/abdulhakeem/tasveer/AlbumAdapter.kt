package com.abdulhakeem.tasveer

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abdulhakeem.tasveer.data.Album
import com.abdulhakeem.tasveer.databinding.ItemAlbumBinding
import com.abdulhakeem.tasveer.di.AlbumViewHolder

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