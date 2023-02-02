package uiview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.base_ui.theme.AppTheme
import com.example.presentation.base_ui.R

@Composable
fun CustomAlertDialog(
    modifier: Modifier = Modifier,
    closeDialog: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(AppTheme.colors.systemGraphOnPrimary)
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), horizontalArrangement = Arrangement.Center) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = stringResource(
                        id = R.string.checkIcon
                    )
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.customAlert), fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ColorDots()
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 21.dp, top = 65.dp), horizontalArrangement = Arrangement.Center) {
                Button(
                    modifier = Modifier
                        .width(151.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(31.dp),
                    onClick = closeDialog
                ) {
                    Text(text = stringResource(id = R.string.close), fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun ColorDots() {
    ConstraintLayout() {
        val (lineFirst, lineSecond, lineTrid, firstDot, secontDot, thridDot, fourthDot, firstText, secontText, thridText, fourthText) = createRefs()

        Dot(
            modifier = Modifier.constrainAs(firstDot) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            colorShadow = Color(0xFF055AFF),
            innerCircleColor = Color(0xFF055AFF),
            outCircleColor = Color(0xFF055AFF)
        )

        Dot(
            modifier = Modifier.constrainAs(secontDot) {
                start.linkTo(firstDot.end, 60.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            colorShadow = Color(0xFFD900D0),
            innerCircleColor = Color(0xFFD900D0),
            outCircleColor = Color(0xFFD9D9D9)
        )
        Column(
            modifier = Modifier
                .constrainAs(lineFirst) {
                    start.linkTo(firstDot.end)
                    bottom.linkTo(firstDot.bottom)
                    top.linkTo(firstDot.top)
                    end.linkTo(secontDot.start)
                }
        ) {
            Divider(
                modifier = Modifier.width(60.dp),
                color = Color(0xFF313841),
                thickness = 2.dp
            )
        }
        Dot(
            modifier = Modifier.constrainAs(thridDot) {
                start.linkTo(secontDot.end, 61.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            colorShadow = Color(0xFFFF009A),
            innerCircleColor = Color(0xFFD9D9D9),
            outCircleColor = Color(0xFFFF009A)
        )
        Column(
            modifier = Modifier
                .constrainAs(lineSecond) {
                    start.linkTo(secontDot.end)
                    bottom.linkTo(secontDot.bottom)
                    top.linkTo(secontDot.top)
                    end.linkTo(thridDot.start)
                }
        ) {
            Divider(
                modifier = Modifier.width(61.dp),
                color = Color(0xFF313841),
                thickness = 2.dp
            )
        }
        Dot(
            modifier = Modifier.constrainAs(fourthDot) {
                start.linkTo(thridDot.end, 65.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            colorShadow = Color(0xFF9E00FF),
            innerCircleColor = Color(0xFFD9D9D9),
            outCircleColor = Color(0xFF9E00FF)
        )
        Column(
            modifier = Modifier
                .constrainAs(lineTrid) {
                    start.linkTo(thridDot.end)
                    bottom.linkTo(thridDot.bottom)
                    top.linkTo(thridDot.top)
                    end.linkTo(fourthDot.start)
                }
        ) {
            Divider(
                modifier = Modifier.width(65.dp),
                color = Color(0xFF313841),
                thickness = 2.dp
            )
        }
        Text(
            modifier = Modifier
                .constrainAs(firstText) {
                    start.linkTo(firstDot.start)
                    top.linkTo(firstDot.bottom, 5.dp)
                    end.linkTo(firstDot.end)
                },
            text = stringResource(id = R.string.first)
        )
        Text(
            modifier = Modifier
                .constrainAs(secontText) {
                    start.linkTo(secontDot.start)
                    top.linkTo(secontDot.bottom, 5.dp)
                    end.linkTo(secontDot.end)
                },
            text = stringResource(id = R.string.secont),
            color = Color(0xFFD900D0)
        )
        Text(
            modifier = Modifier
                .constrainAs(thridText) {
                    start.linkTo(thridDot.start)
                    top.linkTo(thridDot.bottom, 5.dp)
                    end.linkTo(thridDot.end)
                },
            text = stringResource(id = R.string.thrid)
        )
        Text(
            modifier = Modifier
                .constrainAs(fourthText) {
                    start.linkTo(fourthDot.start)
                    top.linkTo(fourthDot.bottom, 5.dp)
                    end.linkTo(fourthDot.end)
                },
            text = stringResource(id = R.string.first)
        )
    }
}

@Composable
fun Dot(
    modifier: Modifier = Modifier,
    colorShadow: Color = Color.Red,
    innerCircleColor: Color = Color.White,
    outCircleColor: Color = Color.Black
) {
    Column(
        modifier = modifier
            .shadow(10.dp, shape = CircleShape, spotColor = colorShadow)
    ) {
        Column(modifier = Modifier.background(outCircleColor).padding(2.dp)) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(innerCircleColor)

            )
        }
    }
}

@Preview
@Composable
private fun CustomAlertDialogPreview() {
    CustomAlertDialog(closeDialog = {})
}
