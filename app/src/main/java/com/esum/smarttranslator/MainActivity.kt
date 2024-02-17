package com.esum.smarttranslator

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.esum.common.constraints.CardFeature
import com.esum.common.constraints.ResultConstraints
import com.esum.common.constraints.TestFuture
import com.esum.core.web_socket.LogService
import com.esum.core.web_socket.WebSocketListener
import com.esum.smarttranslator.navigation.AppNavigation
import com.esum.smarttranslator.navigation.BottomNavigationItem
import com.esum.smarttranslator.navigation.NavigationProvider
import com.esum.smarttranslator.ui.theme.SmartTranslatorTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationProvider: NavigationProvider

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(this , arrayOf(Manifest.permission.POST_NOTIFICATIONS) , 0)
        }


        val intent = Intent(this , LogService::class.java)


        setContent {


            val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

            SmartTranslatorTheme {
                val windowSize = calculateWindowSizeClass(this)
                val navController = rememberNavController()
                var navigationSelectedItem by remember {
                    mutableIntStateOf(0)
                }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                when (navBackStackEntry?.destination?.route) {
                    CardFeature.homeScreenRoute -> {
                        bottomBarState.value = true
                    }

                    TestFuture.testScreenRoute -> {
                        bottomBarState.value = true
                    }

                    else -> {
                        bottomBarState.value = false
                    }
                }


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Scaffold(modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            AnimatedVisibility(
                                visible = bottomBarState.value,
                                enter = slideInVertically(initialOffsetY = { it }),
                                exit = slideOutVertically(targetOffsetY = { it }),
                            ) {


                                NavigationBar {
                                    //getting the list of bottom navigation items for our data class
                                    BottomNavigationItem().bottomNavigationItems()
                                        .forEachIndexed { index, navigationItem ->

                                            //iterating all items with their respective indexes
                                            NavigationBarItem(

                                                colors = NavigationBarItemDefaults.colors(
                                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(
                                                        alpha = 0.5f
                                                    ),
                                                    selectedIconColor = MaterialTheme.colorScheme.onPrimary
                                                ),
                                                selected = index == navigationSelectedItem,
                                                label = {
                                                    Text(text = stringResource(navigationItem.label))
                                                },
                                                icon = {
                                                    Icon(
                                                        imageVector = navigationItem.icon,
                                                        contentDescription = stringResource(
                                                            navigationItem.label
                                                        )
                                                    )
                                                },
                                                onClick = {
                                                    intent.action = LogService.Actions.START.toString()
                                                    startService(intent)
                                                    navigationSelectedItem = index
                                                    navController.navigate(navigationItem.route) {
                                                        popUpTo(navController.graph.findStartDestination().id) {
                                                            saveState = true
                                                        }
                                                        launchSingleTop = true
                                                        restoreState = true
                                                    }
                                                }
                                            )
                                        }
                                }
                            }
                        }) { paddingValues ->

                        AppNavigation(
                            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                            navigationProvider = navigationProvider,
                            navController = navController,
                            windowSizeClass = windowSize,
                        )
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartTranslatorTheme {
        Greeting("Android")
    }
}