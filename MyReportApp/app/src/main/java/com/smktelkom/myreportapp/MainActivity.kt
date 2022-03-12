package com.smktelkom.myreportapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCrash = findViewById<Button>(R.id.btn_crash)

        btnCrash.setOnClickListener{
            throw RuntimeException("Test Crash")
        }
    }
}