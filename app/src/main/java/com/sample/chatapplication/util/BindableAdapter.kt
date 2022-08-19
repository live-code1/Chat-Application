package com.sample.chatapplication.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.sample.chatapplication.R

@BindingAdapter("imageSrc")
fun setImage(image: ImageView, imageUrl: String?) {
    if (image!=null&&!imageUrl.isNullOrEmpty()){
        val glideUrl = GlideUrl(
            imageUrl, LazyHeaders.Builder()
                .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36"
                )
                .build()
        )

        Glide.with(image)
            .load(glideUrl)
            .placeholder(R.drawable.img_place_holder)
            .centerCrop()
            .into(image)
    }
}