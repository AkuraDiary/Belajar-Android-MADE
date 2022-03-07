package com.asthiseta.misc

import android.view.View
import com.asthiseta.favorite.databinding.FragmentFavoriteBinding


interface ShowStates {
    fun favLoading(bindingFav : FragmentFavoriteBinding? = null) //: Int? = null
    fun favSuccess(bindingFav: FragmentFavoriteBinding? = null) //: Int? = null
    fun favError(bindingFav: FragmentFavoriteBinding? = null, message : String?) //: Int? = null

    val gone : Int
        get() = View.GONE

    val visible : Int
        get() = View.VISIBLE
}