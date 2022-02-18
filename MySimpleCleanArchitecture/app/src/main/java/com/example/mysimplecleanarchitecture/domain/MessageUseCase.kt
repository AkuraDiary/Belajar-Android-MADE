package com.example.mysimplecleanarchitecture.domain

interface MessageUseCase {
    fun getMessage(name:String) : MessageEntity
}