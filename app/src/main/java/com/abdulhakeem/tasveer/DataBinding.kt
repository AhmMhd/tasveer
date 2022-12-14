package com.abdulhakeem.tasveer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("bind:adapter")
fun RecyclerView.setAdapter(adapter: BaseAdapter<*, *>) {
    this.adapter = adapter
}

@BindingAdapter("bind:load")
fun ImageView.load(path: String) {
    Glide.with(context).load(path)
        .transform(MultiTransformation(CenterCrop(), RoundedCorners(12)))
        .sizeMultiplier(0.5f).into(this)
}