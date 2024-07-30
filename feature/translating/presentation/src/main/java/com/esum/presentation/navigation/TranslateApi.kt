package com.esum.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.esum.feature_api.FeatureApi

interface TranslateApi  : FeatureApi{
}

class TranslationApiImpl : TranslateApi {

    override fun registerGraph(
        navController: NavController,
        navGraphBuilder: NavGraphBuilder,
        windowSizeClass: WindowSizeClass
    ) {
         InternalTranslationApi.registerGraph(navController, navGraphBuilder, windowSizeClass)
    }
}