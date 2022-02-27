package com.dicoding.mysimplelogin.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.dicoding.MyApplication
import com.dicoding.core.SessionManager
import com.dicoding.core.UserRepository
import com.example.mysimplelogin.chat.R
import javax.inject.Inject

class ChatActivity : AppCompatActivity() {

    @Inject
    lateinit var  userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val tvChat = findViewById<TextView>(R.id.tv_chat)
        //val sesi = SessionManager(this)

    }
}