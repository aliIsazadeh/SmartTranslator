package com.esum.core.ui.component

import android.health.connect.datatypes.units.Percentage
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    percentage: Float,
    count: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = title, style = MaterialTheme.typography.labelSmall.copy(color = mainColor))
            Text(text = count, style = MaterialTheme.typography.labelSmall.copy(color = mainColor))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = subColor, shape = MaterialTheme.shapes.small)
        ) {
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth(if(percentage.isNaN()){0.0f}else percentage)
                    .background(color = mainColor, shape = MaterialTheme.shapes.small)
            )
        }
    }
}


@Preview
@Composable
fun LineBarPreview() {

    SmartTranslatorTheme {
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            LineBar(
                title = "main card",
                mainColor = MaterialTheme.colorScheme.primary,
                subColor = MaterialTheme.colorScheme.primaryContainer,
                percentage = 0.6f,
                count = "212"
            )
        }
    }

}