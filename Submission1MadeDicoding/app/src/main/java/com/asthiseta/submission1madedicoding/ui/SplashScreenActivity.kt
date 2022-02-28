package com.asthiseta.submission1madedicoding.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.asthiseta.submission1madedicoding.R
import com.asthiseta.submission1madedicoding.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashBinding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        supportActionBar?.hide() //hiding the action bar
        val logo = splashBinding.logo
        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        logo.startAnimation(slideAnim)

        val background = object :Thread(){
            override fun run() {
                try {
                    /* simulating some workloads here */
                    sleep(2500)
                    /* simulating some workloads here */

                    //continue into next activity
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}