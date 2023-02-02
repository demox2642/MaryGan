package uiview

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base_ui.theme.AppTheme
import ext.toPercentString

@Preview
@Composable
private fun ProgressIndicatorPreview() {
    val gradientColors: List<Color> = listOf(
        AppTheme.colors.systemGraphOnPrimary,
        AppTheme.colors.controlGraphBlue,
        AppTheme.colors.controlGraphBlueDark
    )
    CustomProgressBar(
        title = "Loading...",
        gradientColors = gradientColors,
        downloadedPercentage = 0.7f,
        viewClip = 5.dp,
        viewHeit = 30.dp,
        textSize = 22.sp
    )
}

@Composable
fun CustomProgressBar(
    modifier: Modifier = Modifier,
    backgroundIndicatorColor: Color? = Color.LightGray.copy(alpha = 0.3f),
    animationDuration: Int = 1000,
    animationDelay: Int = 10,
    downloadedPercentage: Float = 0.5f,
    gradientColors: List<Color>,
    title: String,
    viewHeit: Dp,
    viewClip: Dp,
    textSize: TextUnit ,
) {
    val animateNumber = animateFloatAsState(
        targetValue = downloadedPercentage * 100,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, color = AppTheme.colors.systemTextPrimary, fontSize = textSize)
            Text(
                "${downloadedPercentage.toPercentString()}",
                color = AppTheme.colors.systemTextPrimary,
                fontSize = textSize
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier.height(viewHeit).clip(RoundedCornerShape(viewClip))
        ) {
            Canvas(
                modifier
                    .fillMaxSize()
            ) {
                // Background indicator
                drawLine(
                    color = backgroundIndicatorColor!!,
                    cap = StrokeCap.Square,
                    strokeWidth = size.height * 2,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = size.width, y = 0f)
                )

                val progress =
                    (animateNumber.value / 100) * size.width // size.width returns the width of the canvas

                drawLine(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    ),
                    cap = StrokeCap.Square,
                    strokeWidth = size.height * 2,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = progress, y = 0f)
                )
            }
        }
    }
}
