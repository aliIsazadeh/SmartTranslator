package com.esum.smarttranslator.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.esum.common.constraints.CardFeature
import com.esum.common.constraints.TestFuture
import com.esum.smarttranslator.R

data class BottomNavigationItem(
    val label: Int = R.string.home,
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {

    fun bottomNavigationItems(): List<BottomNavigationItem> {

        return listOf(
            BottomNavigationItem(
                label = R.string.home,
                icon = Icons.Filled.Home,
                route = CardFeature.nestedRoute
            ),
            BottomNavigationItem(
                label = R.string.test,
                icon = Icons.Filled.Face,
                route = TestFuture.nestedRoute
            )
        )
    }
}