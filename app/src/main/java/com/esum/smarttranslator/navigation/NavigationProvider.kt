package com.esum.smarttranslator.navigation

import com.esum.feature.card.presentation.navigation.CardApi
import com.esum.feature.translator.presentation.navigation.TranslateApi
import com.esum.smarttranslator.navigationTesting.TestApi

data class NavigationProvider(
    val cardApi : CardApi ,
    val testApi: TestApi ,
    val translateApi : TranslateApi
)