package com.esum.smarttranslator.navigationTesting

import com.esum.feature.card.presentation.navigation.CardApi
import com.esum.feature.card.presentation.navigation.CardApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object TestApiModule {

    @Provides
    fun provideTestApiModule() : TestApi {
        return TestApiImpl()
    }

}