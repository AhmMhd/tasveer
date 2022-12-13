package com.abdulhakeem.tasveer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("bind:adapter")
fun RecyclerView.setAdapter(adapter: BaseAdapter<*, *>) {
    this.adapter = adapter
}

@BindingAdapter("bind:load")
fun ImageView.load(path: String) = Glide.with(context).load(path).into(this)