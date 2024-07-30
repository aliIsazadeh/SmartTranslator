package com.esum.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.esum.core.ui.component.ResizableTextView
import com.esum.core.ui.topbar.DefaultTopBar
import com.esum.core.ui.use
import com.esum.feature.card.presentation.addingCard.viewmodel.CardAddingContract
import com.esum.feature.card.presentation.component.InfoTextFiled
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
    viewModel.savedStateHandle["text"] = text
    val (state, effect, event) = use(viewModel = viewModel)
    TranslateScreen(
        state = state,
        effect = effect,
        event = event,
        onTextChange = viewModel::onChangeText,
        onTranslatedTextChange = viewModel::onTranslateChange,
        navController = navController
    )
}

@Composable
fun TranslateScreen(
    state: TranslateContract.STATE,
    effect: Flow<TranslateContract.EFFECT>,
    event: (TranslateContract.EVENT) -> Unit,
    onTextChange : (String) -> Unit,
    onTranslatedTextChange : (String) -> Unit,
    navController: NavController
) {

    val keyboardController = LocalSoftwareKeyboardController.current


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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InfoTextFiled(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("original_text_field"),
                    value = state.text,
                    onValueChange = { onTextChange(it) },
                    hint = stringResource(
                        id = R.string.selected_text
                    ),
                    maxLine = 2,
                    nullable = false
                )

                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("online_translate_tag"),
                    onClick = {
                        event.invoke(TranslateContract.EVENT.Translate(state.text))
                        keyboardController?.hide()
                    }) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.height(IntrinsicSize.Min)
                    ) {
                        ResizableTextView(
                            text = stringResource(com.esum.feature.card.presentation.R.string.translate_online),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "online translate",
                            modifier = Modifier.size(20.dp)
                        )

                    }
                }

                InfoTextFiled(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    value = state.translatedText,
                    onValueChange = onTranslatedTextChange,
                    hint = stringResource(
                        id = com.esum.feature.card.presentation.R.string.add_description_here
                    ),
                    maxLine = 4,
                    nullable = true,

                )
            }
        }
    }

}

