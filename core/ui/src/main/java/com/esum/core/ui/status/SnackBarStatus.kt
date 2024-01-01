package com.esum.core.ui.status

import androidx.compose.ui.graphics.Color

enum class SnackBarStatus(val color: Color) {

    Success(color = Color(0, 255, 39, 185)),
    Warning(color = Color(255, 230, 0, 185)),
    Error(color = Color(255, 30, 0, 185))

}