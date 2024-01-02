package com.esum.smarttranslator.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.esum.common.constraints.CardFeature

@Composable
fun AppNavigation(navController: NavHostController, navigationProvider: NavigationProvider , windowSizeClass: WindowSizeClass) {

    NavHost(navController = navController, startDestination = CardFeature.nestedRoute){
        navigationProvider.cardApi.registerGraph(
           navController =  navController, this , windowSizeClass = windowSizeClass
        )


    }

}