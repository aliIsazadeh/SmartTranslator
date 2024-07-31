package com.esum.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.esum.common.constraints.TranslateFeature
import com.esum.feature_api.FeatureApi
import com.esum.presentation.screen.TranslateScreen

internal object InternalTranslationApi : FeatureApi {

    override fun registerGraph(
        navController: NavController,
        navGraphBuilder: NavGraphBuilder,
        windowSizeClass: WindowSizeClass
    ) {

        navGraphBuilder.navigation(
            startDestination = TranslateFeature.translatePageRoute ,
            route = TranslateFeature.nestedRoute
        ) {
            composable(route = TranslateFeature.translatePageRoute + "/{text}"){  navBackstack ->
                val text : String = navBackstack.arguments?.getString("text") ?:""
                TranslateScreen(navController = navController, windowSizeClass = windowSizeClass, text = text)
            }

        }

    }
}