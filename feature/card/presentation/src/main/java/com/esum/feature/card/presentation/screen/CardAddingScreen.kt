package com.esum.feature.card.presentation.screen


import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.request.repeatCount
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.CollectInLaunchedEffect
import com.esum.core.ui.component.Picker
import com.esum.core.ui.theme.SmartTranslatorTheme
import com.esum.core.ui.topbar.DefaultTopBar
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
        viewModel::onTranslationChange,
        viewModel::onSentenceChange
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
    onSentenceTextChange: (String) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()


    effect.CollectInLaunchedEffect { effect ->
        when (effect) {
            is CardAddingContract.Effect.ShowSnackBar -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(effect.message, "Ok")
                }
            }
        }
    }
    Scaffold(topBar = {
        DefaultTopBar(
            leftComposable = {
                IconButton(onClick = { event.invoke(CardAddingContract.Event.backEvent) }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "backBtn")
                }
            },
            rightComposable = {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = R.drawable.send_message)
                            .apply(block = fun ImageRequest.Builder.() {
                                repeatCount(2)
                                size(60)
                            }).build(),
                        imageLoader = imageLoader
                    ),
                    contentDescription = null,
                )
            },
            title = stringResource(id = R.string.add_card)
        )
    }, containerColor = MaterialTheme.colorScheme.background, snackbarHost = {
    }) { padding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = padding.calculateTopPadding())
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    ,
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
                Picker(
                    modifier = Modifier.weight(0.2f),
                    items = listOf(
                        Pair<String, Int>("fa", R.drawable.iran),
                        Pair<String, Int>("en", R.drawable.united_kingdom),
                        Pair<String, Int>("fr", R.drawable.france),
                        Pair<String, Int>("it", R.drawable.italy),
                        Pair<String, Int>("sa", R.drawable.saudi_arabia),
                        Pair<String, Int>("ja", R.drawable.japan),
                        ),
                    textStyle = MaterialTheme.typography.labelSmall
                )

//                Card(
//                    modifier = Modifier
//                        .weight(0.2f)
//                        .fillMaxSize()
//                        .padding(vertical = 8.dp),
//                    shape = MaterialTheme.shapes.extraSmall,
//                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
//
//                ) {
//
//                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth().height(300.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoTextFiled(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(top = 16.dp, bottom = 16.dp),
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
                            .weight(0.5f)
                            .fillMaxSize(),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                        shape = MaterialTheme.shapes.extraSmall,
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {

                    }
                    Picker(modifier = Modifier
                        .weight(0.5f),
                        items = listOf(
                            Pair<String, Int>("fa", R.drawable.iran),
                            Pair<String, Int>("en", R.drawable.united_kingdom),
                            Pair<String, Int>("fr", R.drawable.france),
                            Pair<String, Int>("it", R.drawable.italy),
                            Pair<String, Int>("sa", R.drawable.saudi_arabia),
                            Pair<String, Int>("ja", R.drawable.japan),
                        ),
                        textStyle = MaterialTheme.typography.labelSmall
                    )

                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.small
                    )
            )

            InfoTextFiled(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                value = state.card.sentence,
                onValueChange = onSentenceTextChange,
                hint = stringResource(
                    id = R.string.add_description_here
                ),
                maxLine = 4,
                nullable = true
            )


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
            onTranslationTextChange = {},
            onSentenceTextChange = {}
        )
    }

}