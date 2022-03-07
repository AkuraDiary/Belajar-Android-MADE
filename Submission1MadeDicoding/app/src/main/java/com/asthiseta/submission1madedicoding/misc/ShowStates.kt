package com.asthiseta.submission1madedicoding.misc

import android.view.View
import com.asthiseta.submission1madedicoding.databinding.*


interface ShowStates {
    fun homeLoading(bindingHome : FragmentHomeBinding? = null) //: Int? = null
    fun homeSuccess(bindingHome: FragmentHomeBinding? = null) //: Int? = null
    fun homeError(bindingHome: FragmentHomeBinding? = null, message : String?) //: Int? = null

    val gone : Int
        get() = View.GONE

    val visible : Int
        get() = View.VISIBLE
}