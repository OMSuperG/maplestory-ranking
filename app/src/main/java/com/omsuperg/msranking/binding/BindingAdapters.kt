package com.omsuperg.msranking.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun loadImageWithUri(imageView: ImageView, imageUri: String?) {
    if (imageUri.isNullOrEmpty()) {
        imageView.setImageDrawable(null)
    } else {
        Glide.with(imageView.context).load(Uri.parse(imageUri)).into(imageView)
    }
}