package com.smktelkom.myreportapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import java.lang.Exception
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("Test debugging")

        val btnCrash = findViewById<Button>(R.id.btn_crash)

        // Obtain the FirebaseAnalytics instance.
        analytics = Firebase.analytics

        btnCrash.setOnClickListener{
            FirebaseCrashlytics.getInstance().log("Clicked on button")
            FirebaseCrashlytics.getInstance().setCustomKey("str_key", "some data")
            try{
                throw RuntimeException("Test Crash")
            }catch (e:Exception){
                Timber.e("Test non Fatal Exception")
            }
        }
    }
}