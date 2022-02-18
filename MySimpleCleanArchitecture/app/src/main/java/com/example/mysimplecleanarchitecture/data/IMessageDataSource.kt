package com.example.mysimplecleanarchitecture.data

import com.example.mysimplecleanarchitecture.domain.MessageEntity

interface IMessageDataSource{
    fun getMessageFromSource(name : String) : MessageEntity
}