package com.eclecticsIntern.newsApp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.eclecticsIntern.newsApp.R

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).placeholder(R.drawable.e_logo2).into(this)
}