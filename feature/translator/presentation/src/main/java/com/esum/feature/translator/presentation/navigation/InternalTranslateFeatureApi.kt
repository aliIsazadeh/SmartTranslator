package com.esum.feature.translator.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.esum.common.constraints.TranslateFeature
import com.esum.feature.translator.presentation.screen.CameraScreen
import com.esum.feature_api.FeatureApi

//internal object InternalTranslateFeatureApi : FeatureApi {
//    override fun registerGraph(
//        navController: NavController,
//        navGraphBuilder: NavGraphBuilder,
//        windowSizeClass: WindowSizeClass
//    ) {
//        navGraphBuilder.navigation(
//            startDestination = TranslateFeature.translatePageRoute,
//            route = TranslateFeature.nestedRoute
//        ) {
//
//            composable(TranslateFeature.translatePageRoute) {
//                CameraScreen(navController = navController, windowSizeClass = windowSizeClass)
//            }
//
//        }
//    }
//}