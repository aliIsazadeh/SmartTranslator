package com.esum.presentation.screen

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.esum.core.ui.use
import com.esum.presentation.viewmodel.TranslateContract
import com.esum.presentation.viewmodel.TranslateViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun TranslateScreen(
    navController: NavController,
    windowSizeClass: WindowSizeClass,
    viewModel: TranslateViewModel = hiltViewModel()
) {
    val (state, effect, event) = use(viewModel = viewModel)
}
@Composable
fun TranslateScreen(
    state: TranslateContract.STATE,
    effect: Flow<TranslateContract.EFFECT>,
    event: (TranslateContract.EVENT) -> Unit
) {




}

