package com.asthiseta.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mapbox.mapboxsdk.Mapbox

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(applicationContext, getString(R.string.mapbox_acces_token))
        setContentView(R.layout.activity_main)
    }
}