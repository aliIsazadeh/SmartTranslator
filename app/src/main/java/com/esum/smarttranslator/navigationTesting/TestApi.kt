package com.esum.smarttranslator.navigationTesting

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.esum.feature_api.FeatureApi

interface TestApi : FeatureApi {

}
class TestApiImpl : TestApi {
    override fun registerGraph(navController: NavController, navGraphBuilder: NavGraphBuilder ,windowSizeClass : WindowSizeClass) {
        InternalTestFeatureApi.registerGraph(
            navController, navGraphBuilder, windowSizeClass
        )
    }
}