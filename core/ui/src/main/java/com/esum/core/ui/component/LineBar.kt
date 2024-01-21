package com.esum.core.ui.component

import android.health.connect.datatypes.units.Percentage
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esum.core.ui.theme.SmartTranslatorTheme

@Composable
fun LineBar(
    modifier: Modifier = Modifier,
    title: String,
    mainColor: Color,
    subColor: Color,
    percentage: Double,
    count: String
) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = title, style = MaterialTheme.typography.bodySmall.copy(color = mainColor))
            Text(text = count, style = MaterialTheme.typography.bodySmall.copy(color = mainColor))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = subColor, shape = MaterialTheme.shapes.small)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(percentage.toFloat())
                    .background(color = mainColor, shape = MaterialTheme.shapes.small)
            )
        }
    }
}


@Preview
@Composable
fun LineBarPreview() {

    SmartTranslatorTheme {
        LineBar(
            title = "main card",
            mainColor = MaterialTheme.colorScheme.primary,
            subColor = MaterialTheme.colorScheme.primaryContainer,
            percentage = 0.6,
            count = "212"
        )
    }

}