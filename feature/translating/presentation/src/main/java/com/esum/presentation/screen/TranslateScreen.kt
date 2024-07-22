package com.esum.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.esum.core.ui.topbar.DefaultTopBar
import com.esum.core.ui.use
import com.esum.presentation.R
import com.esum.presentation.viewmodel.TranslateContract
import com.esum.presentation.viewmodel.TranslateViewModel
import kotlinx.coroutines.flow.Flow
import com.esum.core.ui.R as UiR


@Composable
fun TranslateScreen(
    navController: NavController,
    windowSizeClass: WindowSizeClass,
    viewModel: TranslateViewModel = hiltViewModel(),
    text: String?
) {
    val (state, effect, event) = use(viewModel = viewModel)
}

@Composable
fun TranslateScreen(
    state: TranslateContract.STATE,
    effect: Flow<TranslateContract.EFFECT>,
    event: (TranslateContract.EVENT) -> Unit,
    text: String?,

    navController: NavController
) {

    Scaffold(topBar = {
        DefaultTopBar(
            title = stringResource(id = R.string.translating),
            rightComposable = {
                val composition by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(
                        UiR.raw.translating
                    )
                )

                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(36.dp),
                    iterations = 3,
                    restartOnPlay = false,
                )

            },
            leftComposable = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "backBtn")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }) { paddingValues ->

        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

//                TextField(value = text, onValueChange = )


            }
        }


    }


}

