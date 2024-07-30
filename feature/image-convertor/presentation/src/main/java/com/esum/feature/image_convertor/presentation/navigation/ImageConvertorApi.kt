package com.esum.feature.image_convertor.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.esum.feature_api.FeatureApi

interface ImageConvertorApi : FeatureApi {

}

class ImageConvertorApiImpl : ImageConvertorApi {
    override fun registerGraph(
        navController: NavController,
        navGraphBuilder: NavGraphBuilder,
        windowSizeClass: WindowSizeClass
    ) {
        InternalTranslateFeatureApi.registerGraph(
            navController, navGraphBuilder, windowSizeClass
        )
    }

}