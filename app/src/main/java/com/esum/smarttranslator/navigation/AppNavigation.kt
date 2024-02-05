package com.esum.smarttranslator.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.esum.common.constraints.CardFeature

@Composable
fun AppNavigation(
    modifier: Modifier,
    navController: NavHostController,
    navigationProvider: NavigationProvider,
    windowSizeClass: WindowSizeClass
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CardFeature.nestedRoute
    ) {
        navigationProvider.cardApi.registerGraph(
            navController = navController, this, windowSizeClass = windowSizeClass
        )
        navigationProvider.testApi.registerGraph(
            navController, this, windowSizeClass
        )
        navigationProvider.translateApi.registerGraph(navController, this, windowSizeClass)
    }

}