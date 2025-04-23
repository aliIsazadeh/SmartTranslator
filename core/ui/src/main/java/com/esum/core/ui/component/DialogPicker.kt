package com.esum.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.wear.compose.material.Card
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.language.bm.Languages

@Composable
fun DialogPicker(
    modifier: Modifier = Modifier,
    items: List<Pair<Languages, Int>>,
    selectLanguage: (Languages) -> Unit,
    onDialogCancel: (Boolean) -> Unit
) {

    Dialog(onDismissRequest = { onDialogCancel(false) }) {

        Card(shape = MaterialTheme.shapes.small, onClick = {}) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(40.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { item ->
                    Card(onClick = {
                        selectLanguage(item.first)
                        onDialogCancel(false)
                    }) {
                        Image(
                            painter = painterResource(id = item.second),
                            contentDescription = "flag image",
                            Modifier
                                .height(30.dp)
                        )
                    }
                }
            }
        }
    }


}