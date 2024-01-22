package com.esum.smarttranslator.navigationTesting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.esum.common.constraints.CardFeature
import com.esum.common.constraints.TestFuture
import com.esum.feature.card.presentation.addingCard.screen.CardAddingScreen
import com.esum.feature_api.FeatureApi

internal object InternalTestFeatureApi : FeatureApi {

    override fun registerGraph(
        navController: NavController,
        navGraphBuilder: NavGraphBuilder,
        windowSizeClass: WindowSizeClass
    ) {
        navGraphBuilder.navigation(
            startDestination = TestFuture.testScreenRoute,
            route = TestFuture.nestedRoute
        ) {
            composable(TestFuture.testScreenRoute) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "test")
                }
            }

        }
    }
}