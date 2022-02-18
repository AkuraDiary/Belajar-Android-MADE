package com.example.mysimplecleanarchitecture.data

import com.example.mysimplecleanarchitecture.domain.IMessageRepository
import com.example.mysimplecleanarchitecture.domain.MessageEntity

class MessageRepository(private val messageDataSource: IMessageDataSource) : IMessageRepository{
    override fun getWelcomeMessage(name: String): MessageEntity {
        return messageDataSource.getMessageFromSource(name)
    }
}