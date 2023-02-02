package uiview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.base_ui.theme.AppTheme

@Composable
fun TimeItem(
    modifier: Modifier = Modifier,
    name: String,
    value: String
) {
    ConstraintLayout(modifier = modifier) {
        val (card, text) = createRefs()
        Card(
            modifier = Modifier
                .size(width = 68.dp, height = 74.dp)
                .clip(RoundedCornerShape(12.dp))
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }

        ) {
            Row(
                modifier = Modifier
                    .background(Color(0xFF27292C))
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = value,
                    color = AppTheme.colors.systemTextOnPrimary,
                    fontSize = 32.sp,
                    style = AppTheme.typography.buttonM
                )
            }
        }

        Text(
            text = name,
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(card.start)
                    top.linkTo(card.bottom, 6.dp)
                    end.linkTo(card.end)
                },
            color = AppTheme.colors.systemTextPrimary,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun TimeItemPreview() {
    TimeItem(name = "min", value = "25")
}
