package com.esum.database.di

import android.content.Context
import androidx.room.Room
import com.esum.database.database.TranslateDB
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(replaces = [DatabaseModule::class] , components = [SingletonComponent::class])
object DatabaseModuleTest {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) : TranslateDB {
        return Room.inMemoryDatabaseBuilder(
            context = context , TranslateDB::class.java
        ).build()
    }

}