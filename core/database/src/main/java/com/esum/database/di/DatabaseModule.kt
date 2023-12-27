package com.esum.database.di

import android.content.Context
import androidx.room.Room
import com.esum.database.dao.CardDao
import com.esum.database.dao.ProfileDao
import com.esum.database.database.TranslateDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTranslateDB(@ApplicationContext context: Context): TranslateDB {
        return Room.databaseBuilder(context, TranslateDB::class.java, "TranslateDB").build()
    }


}