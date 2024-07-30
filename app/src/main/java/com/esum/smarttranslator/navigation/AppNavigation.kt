package com.esum.smarttranslator.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.esum.common.constraints.CardFeature
import com.esum.common.constraints.HomeFeature
import com.esum.smarttranslator.splash.SliderScreen
import com.esum.smarttranslator.splash.SplashScreen

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
        startDestination = HomeFeature.splashScreen
    ) {
        composable(HomeFeature.splashScreen){
            SplashScreen(navController = navController)
        }

        composable(HomeFeature.sliderScreen){

            SliderScreen(navController = navController)
        }
        navigationProvider.cardApi.registerGraph(
            navController = navController, this, windowSizeClass = windowSizeClass
        )
        navigationProvider.testApi.registerGraph(
            navController, this, windowSizeClass
        )
        navigationProvider.imageConvertorApi.registerGraph(navController, this, windowSizeClass)
    }

}