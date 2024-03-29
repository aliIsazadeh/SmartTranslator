package com.esum.feature.card.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.esum.common.constraints.CardFeature
import com.esum.feature.card.presentation.addingCard.screen.CardAddingScreen
import com.esum.feature.card.presentation.home.screen.CardHomeScreen
import com.esum.feature.card.presentation.reviewCards.screen.ReviewCardsScreen
import com.esum.feature_api.FeatureApi

internal object InternalCardFeatureApi : FeatureApi {

    override fun registerGraph(
        navController: NavController,
        navGraphBuilder: NavGraphBuilder,
        windowSizeClass: WindowSizeClass
    ) {
        navGraphBuilder.navigation(
            startDestination = CardFeature.homeScreenRoute,
            route = CardFeature.nestedRoute
        ) {

            composable(CardFeature.homeScreenRoute) {
                CardHomeScreen(windowSizeClass = windowSizeClass, navController = navController)
            }
            composable(CardFeature.addCardScreenRoute) {
                CardAddingScreen(windowSize = windowSizeClass, navController = navController)
            }
            composable(CardFeature.reviewCardsScreenRoute) {
                ReviewCardsScreen(windowSizeClass = windowSizeClass, navController = navController)
            }

        }
    }
}