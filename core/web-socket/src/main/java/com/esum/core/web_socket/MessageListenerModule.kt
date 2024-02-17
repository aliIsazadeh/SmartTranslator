package com.esum.core.web_socket

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MessageListenerModule {

    @Provides
    @Singleton
    fun provideMessageListener() : MessageListener{
        return MessageListener("api.tiingo.com" , Dispatchers.IO)
    }

}