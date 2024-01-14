package com.esum.feature.card.presentation.addingCard.screen


import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
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
import com.esum.core.ui.component.CircularIndeterminateProgressBar
import com.esum.core.ui.component.DefaultSnackbar
import com.esum.core.ui.component.GenericDialog
import com.esum.core.ui.theme.SmartTranslatorTheme
import com.esum.core.ui.topbar.DefaultTopBar
import com.esum.core.ui.use
import com.esum.feature.card.presentation.R
import com.esum.feature.card.presentation.component.InfoTextFiled
import com.esum.feature.card.presentation.component.Picker
import com.esum.feature.card.presentation.component.WordDescriptionItem
import com.esum.feature.card.presentation.addingCard.viewmodel.AddingCardViewModel
import com.esum.feature.card.presentation.addingCard.viewmodel.CardAddingContract
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
        viewModel::onSentenceChange,
        onOriginalLanguageSelect = viewModel::onOriginalChange,
        onTranslateLanguageSelect = viewModel::onTranslationChange,
        onAudioChange = viewModel::onAudioPlays
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
    onSentenceTextChange: (String) -> Unit,
    onOriginalLanguageSelect: (Languages) -> Unit,
    onTranslateLanguageSelect: (Languages) -> Unit,
    onAudioChange: (String) -> Unit

) {

    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val translateFocusRequester = remember { FocusRequester() }
    val originalFocusRequester = remember { FocusRequester() }


    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val translateTextFiledFocus by remember {
        mutableStateOf(false)
    }


//    var mediaPlayer by remember { mutableStateOf(MediaPlayer()) }


    effect.CollectInLaunchedEffect { effect ->
        when (effect) {

            is CardAddingContract.Effect.ShowSnackBar -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        context.getString(effect.message),
                        actionLabel = context.getString(R.string.ok),
                        withDismissAction = true
                    )
                }
            }

            is CardAddingContract.Effect.SetFocusOnTranslateTextFiled -> {
                translateFocusRequester.requestFocus()
            }

            is CardAddingContract.Effect.SetFocusOnOriginalTextFiled -> {
                originalFocusRequester.requestFocus()
            }
        }
    }
    Scaffold(
        topBar = {
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
            DefaultSnackbar(
                modifier = Modifier.testTag("snack_bar"),
                snackbarHostState = snackbarHostState,
                onDismiss = { snackbarHostState.currentSnackbarData?.dismiss() },
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .testTag("save_card_btn")
                    .fillMaxWidth()
                    .padding(16.dp), shape = MaterialTheme.shapes.extraSmall,
                enabled = (state.card.originalText.isNotBlank() && state.card.originalLanguages.key.isNotBlank()),
                onClick = { event.invoke(CardAddingContract.Event.SaveCardEvent) }) {
                Text(
                    text = stringResource(R.string.save_card),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }) { padding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoTextFiled(
                    modifier = Modifier
                        .weight(0.8f)
                        .testTag("original_text_field")
                        .focusRequester(originalFocusRequester),
                    value = state.card.originalText,
                    onValueChange = onOriginalTextChange,
                    hint = stringResource(
                        id = R.string.Add_card_text_here
                    ),
                    maxLine = 2,
                    nullable = false
                )
                Picker(
                    modifier = Modifier
                        .weight(0.2f)
                        .testTag("original_picker"),
                    items = state.availableLanguage,
                    textStyle = MaterialTheme.typography.labelSmall,
                    dividerColor = MaterialTheme.colorScheme.primary,
                    selectLanguage = onOriginalLanguageSelect,
                )

            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("online_translate_tag"),
                onClick = { event.invoke(CardAddingContract.Event.OnlineTranslateEvent) }) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.height(IntrinsicSize.Min)
                ) {
                    Text(text = "translate online", style = MaterialTheme.typography.bodySmall)
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "online translate",
                        modifier = Modifier.size(20.dp)
                    )

                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoTextFiled(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(top = 16.dp, bottom = 16.dp)
                        .testTag("translate_text_field")
                        .focusRequester(translateFocusRequester),
                    value = state.card.translateText,
                    onValueChange = onTranslationTextChange,
                    hint = stringResource(
                        id = R.string.add_translate_text_here
                    ),
                    maxLine = 4,
                    nullable = false
                )

                Picker(
                    modifier = Modifier
                        .weight(0.2f)
                        .testTag("translate_picker"),
                    items = state.availableLanguage,
                    textStyle = MaterialTheme.typography.labelSmall,
                    selectLanguage = onTranslateLanguageSelect,
                )
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
            if (state.card.description == null) {
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
                    nullable = true,
                    trailingIcon = {
                        IconButton(onClick = { event.invoke(CardAddingContract.Event.GenerateSentenceEvent) }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "generate sentence",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
            } else {
                var isPlaying by remember { mutableStateOf(false) }
                WordDescriptionItem(
                    value = state.card.description,
                    onPlaySoundClick = {
                        try {
                            val mediaPlayer = MediaPlayer().apply {
                                setAudioAttributes(
                                    AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                        .setUsage(AudioAttributes.USAGE_MEDIA)
                                        .build()
                                )
                                setDataSource(it)
                                prepare() // might take long! (for buffering, etc)
                                start()
                            }
                        } catch (e: Exception) {

                            Log.e("mediaPlayer", "CardAddingScreen: ${e.message}")
                        }
                    } ,
                    onSearchClick = {event.invoke(CardAddingContract.Event.GenerateSentenceEvent)})
            }
        }
        CircularIndeterminateProgressBar(isVisible = state.loading)

        if (state.errors != null) {
            state.errors.apply {
                GenericDialog(
                    onDismiss = { onDismiss },
                    title = title,
                    sticker = sticker,
                    positiveAction = positiveAction,
                    negativeAction = negativeAction,
                    description = description
                )
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
            onTranslationTextChange = {},
            onSentenceTextChange = {},
            onOriginalLanguageSelect = {},
            onTranslateLanguageSelect = {},
            onAudioChange = {}
        )
    }

}