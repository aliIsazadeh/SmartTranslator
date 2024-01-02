package com.esum.feature.card.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.esum.feature_api.FeatureApi

interface CardApi : FeatureApi {

}
class CardApiImpl : CardApi {
    override fun registerGraph(navController: NavController, navGraphBuilder: NavGraphBuilder ,windowSizeClass : WindowSizeClass) {
        InternalCardFeatureApi.registerGraph(
            navController, navGraphBuilder, windowSizeClass
        )
    }
}