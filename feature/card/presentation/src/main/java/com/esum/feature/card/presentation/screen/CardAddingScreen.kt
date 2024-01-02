package com.esum.feature.card.presentation.screen


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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.CollectInLaunchedEffect
import com.esum.core.ui.theme.SmartTranslatorTheme
import com.esum.core.ui.use
import com.esum.feature.card.presentation.R
import com.esum.feature.card.presentation.component.InfoTextFiled
import com.esum.feature.card.presentation.viewmodel.AddingCardViewModel
import com.esum.feature.card.presentation.viewmodel.CardAddingContract
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


@Composable
fun CardAddingScreen(
    windowSize: WindowSizeClass,
    viewModel: AddingCardViewModel = hiltViewModel(),
    navController: NavController
) {
    val (state, effect, event) = use(viewModel = viewModel)
    CardAddingScreen(
        state = state,
        effect = effect,
        event = event,
        viewModel::onOriginalChange,
        viewModel::onTranslationChange
    )

}

@OptIn(InternalCoroutinesApi::class)
@Composable
fun CardAddingScreen(
    state: CardAddingContract.State,
    effect: Flow<CardAddingContract.Effect>,
    event: (CardAddingContract.Event) -> Unit,
    onOriginalTextChange: (String) -> Unit,
    onTranslationTextChange: (String) -> Unit,
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    effect.CollectInLaunchedEffect { effect ->
        when (effect) {
            is CardAddingContract.Effect.ShowSnackBar -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(effect.message, "Ok")
                }
            }
        }
    }
    Scaffold(containerColor = MaterialTheme.colorScheme.background, snackbarHost = {
    }) { padding ->
        padding

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoTextFiled(
                    modifier = Modifier.weight(0.8f),
                    value = state.card.original,
                    onValueChange = onOriginalTextChange,
                    hint = stringResource(
                        id = R.string.Add_card_text_here
                    ),
                    maxLine = 2,
                    nullable = false
                )
                Card(
                    modifier = Modifier.weight(0.2f).fillMaxSize().padding(vertical = 8.dp),
                    shape = MaterialTheme.shapes.extraSmall,
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

                ) {

                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoTextFiled(
                    modifier = Modifier.weight(0.8f).padding(top = 16.dp , bottom = 16.dp),
                    value = state.card.translate,
                    onValueChange = onTranslationTextChange,
                    hint = stringResource(
                        id = R.string.add_translate_text_here
                    ),
                    maxLine = 4,
                    nullable = false
                )
                Column(
                    modifier = Modifier.weight(0.2f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(0.5f).fillMaxSize(),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                        shape = MaterialTheme.shapes.extraSmall,
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {

                    }
                    Card(
                        modifier = Modifier.weight(0.5f).fillMaxSize(),
                        shape = MaterialTheme.shapes.extraSmall,
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        


                    }
                }
            }

        }


    }


}

@Preview
@Composable
fun CardAddingScreenPreview() {

    SmartTranslatorTheme() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        )
        CardAddingScreen(
            state = CardAddingContract.State(),
            effect = flowOf(),
            event = {},
            onOriginalTextChange = {},
            onTranslationTextChange = {}
        )
    }

}