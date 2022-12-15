package com.abdulhakeem.tasveer.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.abdulhakeem.tasveer.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

@BindingAdapter("bind:load")
fun ImageView.load(path: String) {
    Glide.with(context).load(path)
        .placeholder(R.drawable.thumbnail)
        .transform(MultiTransformation(CenterCrop(), RoundedCorners(12)))
        .sizeMultiplier(0.5f).into(this)
}