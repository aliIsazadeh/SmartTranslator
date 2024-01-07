package com.esum.feature.card.presentation.component

import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDirection

@Composable
fun InfoTextFiled(
    modifier: Modifier = Modifier,
    value: String?,
    onValueChange: (String) -> Unit,
    maxLine: Int? = null,
    nullable: Boolean,
    hint: String,
    trailingIcon: @Composable() () -> Unit = {}
) {

    var error by remember {
        mutableStateOf(false)
    }
    var focus by remember {
        mutableStateOf(false)
    }



    LaunchedEffect(focus) {
        if (!nullable && focus) {
            error = value.isNullOrBlank()
        }
    }

    TextField(
        modifier = modifier.onFocusChanged {
            if (it.isFocused) {
                focus = true
            }
        },
        value = value ?: "",
        onValueChange = onValueChange,
        maxLines = maxLine ?: Int.MAX_VALUE,
        textStyle = MaterialTheme.typography.bodyMedium.copy(textDirection = TextDirection.Content),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.background,
            focusedLabelColor = if (error) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,

            ),
        trailingIcon = { trailingIcon() },

        isError = error && value.isNullOrBlank(),
        label = {
            if (hint.isNotBlank() && value.isNullOrBlank()) {
                Text(
                    text = hint, style = MaterialTheme.typography.bodySmall
                )
            }
        })


}