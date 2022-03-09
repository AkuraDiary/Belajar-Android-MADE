package com.asthiseta.core.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.asthiseta.core.R
import com.asthiseta.core.misc.GlideApp
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("avatar")
fun avatar(imageView : ImageView, avatar:String)=
    GlideApp.with(imageView.context)
        .load(avatar)
        .apply(RequestOptions.centerCropTransform().placeholder(R.drawable.ravioli))
        .into(imageView)