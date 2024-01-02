package com.esum.feature.card.presentation.component

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color

@Composable
fun InfoTextFiled(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    maxLine: Int? = null,
    nullable: Boolean,
    hint: String,

    ) {

    var error by remember {
        mutableStateOf(false)
    }

    TextField(
        modifier = modifier.onFocusChanged {
            if (it.isFocused) {
                if (!nullable) {
                    error = value.isBlank()
                }
            }
        },
        value = value,
        onValueChange = onValueChange,
        maxLines = maxLine ?: Int.MAX_VALUE,
        textStyle = MaterialTheme.typography.bodyMedium,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = if (error) MaterialTheme.colorScheme.error else Color.Transparent,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = if (error) MaterialTheme.colorScheme.error else Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.background,
            focusedLabelColor = if (error) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,

            ),

        isError = error,
        label = {
            if (hint.isNotBlank() && value.isBlank()) {
                Text(
                    text = hint, style = MaterialTheme.typography.bodySmall
                )
            }
        })


}