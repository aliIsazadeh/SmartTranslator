package com.esum.feature.card.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import com.esum.feature.card.presentation.R
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import com.esum.feature.card.presentation.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esum.common.lagnuage.Languages
import com.esum.core.ui.theme.SmartTranslatorTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Picker(
    modifier: Modifier = Modifier,
    items: List<Pair<Languages, Int>>,
    state: PickerState = rememberPickerState(),
    startIndex: Int = 0,
    visibleItemsCount: Int = 3,
    textModifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    dividerColor: Color = MaterialTheme.colorScheme.primary,
    selectLanguage : (Languages) -> Unit
) {

    val visibleItemsMiddle = visibleItemsCount / 2
    val listScrollCount = Integer.MAX_VALUE 
    val listScrollMiddle = listScrollCount / 2
    val listStartIndex =
        listScrollMiddle - listScrollMiddle % items.size -  visibleItemsMiddle + startIndex

    fun getItem(index: Int) = items[index % items.size]

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = listStartIndex )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val itemHeightPixels = remember { mutableIntStateOf(0) }
    val itemHeightDp = pixelsToDp(itemHeightPixels.intValue)

    val fadingEdgeGradient = remember {
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.5f to Color.Black,
            1f to Color.Transparent
        )
    }

    LaunchedEffect(listState) {

        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index -> getItem(index + visibleItemsMiddle) }
            .distinctUntilChanged()
            .collect { item ->
                state.selectedItem = item.first.key
                state.itemImage = item.second
                selectLanguage(item.first)
            }
    }

    Box(modifier = modifier) {

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.testTag("lazy_column")
                .fillMaxWidth()
                .height(itemHeightDp * visibleItemsCount)
                .fadingEdge(fadingEdgeGradient)
        ) {
            items(listScrollCount) { index ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                ) {
                    Text(
                        text = getItem(index).first.key,
                        modifier = Modifier
                            .onSizeChanged { size -> itemHeightPixels.value = size.height }
                            .then(textModifier)
                            .weight(0.2f),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Image(
                        painter = painterResource(id = getItem(index).second),
                        contentDescription = "flag image",
                        Modifier
                            .height(30.dp)
                            .weight(0.5f)
                    )
                }
            }
        }

        Divider(
            color = dividerColor,
            modifier = Modifier.offset(y = itemHeightDp * visibleItemsMiddle)
        )

        Divider(
            color = dividerColor,
            modifier = Modifier.offset(y = itemHeightDp * (visibleItemsMiddle + 1))
        )

    }

}

private fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }

@Composable
private fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }

@Composable
fun rememberPickerState() = remember { PickerState() }

class PickerState {
    var selectedItem by mutableStateOf("")
    var itemImage by mutableIntStateOf(R.drawable.iran)
}


@Preview
@Composable
fun PickerPreview() {

    SmartTranslatorTheme {

        Row(
            Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Box(modifier = Modifier.weight(0.8f))


            Picker(
                modifier = Modifier.weight(0.2f),
                items = listOf(
                    Pair<Languages, Int>(Languages.Farsi, R.drawable.iran),
                    Pair<Languages, Int>(Languages.English, R.drawable.united_kingdom),
                    Pair<Languages, Int>(Languages.French, R.drawable.france),
                    Pair<Languages, Int>(Languages.Italian, R.drawable.italy),
                    Pair<Languages, Int>(Languages.Arabic, R.drawable.saudi_arabia),
                    Pair<Languages, Int>(Languages.Japans, R.drawable.japan),


                    ),
                textStyle = MaterialTheme.typography.labelSmall,
                selectLanguage = {},

            )
        }

    }


}