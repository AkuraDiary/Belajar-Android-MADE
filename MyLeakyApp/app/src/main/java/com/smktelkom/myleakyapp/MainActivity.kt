package com.smktelkom.myleakyapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var broadcastReceiver: BroadcastReceiver
    lateinit var tv_power_status  : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_power_status = findViewById(R.id.tv_power_status)
    }

    override fun onStart() {
        super.onStart()
        registerBroadCastReciever()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    private fun registerBroadCastReciever(){
        broadcastReceiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                when(intent?.action){
                    Intent.ACTION_POWER_CONNECTED -> {
                        tv_power_status.text = getString(R.string.power_connected)
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        tv_power_status.text = getString(R.string.power_disconnected)
                    }
                }
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }
}