package com.esum.core.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedCircularProgressIndicator(
    name: String = "",
    size: Dp = 40.dp,
    foregroundIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    shadowColor: Color = Color.LightGray,
    indicatorThickness: Dp = 8.dp,
    dataUsage: Float = 60f,
    animationDuration: Int = 1000,
    dataTextStyle: TextStyle = TextStyle(fontSize = 12.sp),
) {
    // State to hold the data usage value for animation
    var dataUsageRemember by remember {
        mutableFloatStateOf(-1f)
    }

    // State for animating the data usage value
    val dataUsageAnimate = animateFloatAsState(
        targetValue = dataUsageRemember,
        animationSpec = tween(
            durationMillis = animationDuration
        ), label = ""
    )

    // Trigger the LaunchedEffect to start the animation when the composable is first launched.
    LaunchedEffect(Unit) {
        dataUsageRemember = dataUsage
    }

    // Box to hold the entire composable
    Box(
        modifier = Modifier
            .size(size)
            .padding(top = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        // Canvas drawing for the circular progress bar
        Canvas(
            modifier = Modifier.size(size)
        ) {
            // Draw the shadow around the circular progress bar
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(shadowColor, Color.White),
                    center = Offset(x = this.size.width / 2, y = this.size.height / 2),
                    radius = this.size.height / 2
                ),
                radius = this.size.height / 2,
                center = Offset(x = this.size.width / 2, y = this.size.height / 2)
            )

            // Draw the white background of the circular progress bar
            drawCircle(
                color = Color.White,
                radius = (size / 2 - indicatorThickness).toPx(),
                center = Offset(x = this.size.width / 2, y = this.size.height / 2)
            )

            // Calculate and draw the progress indicator
            val sweepAngle = (dataUsageAnimate.value) * 360 / 100
            drawArc(
                color = foregroundIndicatorColor,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Round),
                size = Size(
                    width = (size - indicatorThickness).toPx(),
                    height = (size - indicatorThickness).toPx()
                ),
                topLeft = Offset(
                    x = (indicatorThickness / 2).toPx(),
                    y = (indicatorThickness / 2).toPx()
                )
            )
        }

        // Display text below the circular progress bar
        DisplayText(
            name = name,
            animateNumber = dataUsageAnimate,
            dataTextStyle = dataTextStyle,
        )
    }

    // Spacer to add some padding below the circular progress bar
    Spacer(modifier = Modifier.height(16.dp))
}

/**
 * A private composable function to display the name and data usage percentage text.
 *
 * @param name The name or label associated with the circular progress bar.
 * @param animateNumber The animated data usage percentage value.
 * @param dataTextStyle The style for displaying the data usage text.
 */
@Composable
private fun DisplayText(
    name: String,
    animateNumber: State<Float>,
    dataTextStyle: TextStyle,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        // Display the name with bold font and ellipsis for overflow
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            style = dataTextStyle,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Display the data usage percentage text
        Text(
            text = (animateNumber.value).toInt().toString() + "%",
            style = dataTextStyle
        )
    }
}