package com.asthiseta.submission1madedicoding.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.asthiseta.submission1madedicoding.R
import com.asthiseta.submission1madedicoding.misc.GlideApp
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("avatar")
fun avatar(imageView : ImageView, avatar:String)=
    GlideApp.with(imageView.context)
        .load(avatar)
        .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.ravioli))
        .into(imageView)