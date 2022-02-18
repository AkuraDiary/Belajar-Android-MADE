package com.example.mysimplecleanarchitecture.di

import com.example.mysimplecleanarchitecture.data.IMessageDataSource
import com.example.mysimplecleanarchitecture.data.MessageDataSource
import com.example.mysimplecleanarchitecture.data.MessageRepository
import com.example.mysimplecleanarchitecture.domain.IMessageRepository
import com.example.mysimplecleanarchitecture.domain.MessageInteractor
import com.example.mysimplecleanarchitecture.domain.MessageUseCase

object Injection {
    fun provideUseCase() : MessageUseCase{
        val messageRepository = provideRepository()
        return MessageInteractor(messageRepository)
    }

    private fun provideRepository() : IMessageRepository{
        val messageDataSource = provideDataSource()
        return MessageRepository(messageDataSource)
    }

    private fun provideDataSource() : IMessageDataSource{
        return MessageDataSource()
    }
}