package com.example.second.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.base_ui.theme.AppTheme
import com.example.second.R
import com.example.second.models.UserRaitings
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import uiview.CustomProgressBar
import uiview.TimeItem

@Composable
fun SecondScreen(navHostController: NavHostController) {
    val viewModel: SecondViewModel = hiltViewModel()
    val raitingsList by viewModel.raitingList.collectAsState()
    val timerHour by viewModel.timerHour.collectAsState()
    val timerMin by viewModel.timerMin.collectAsState()
    val timerSecond by viewModel.timerSecond.collectAsState()
    val progressLoading by viewModel.progressLoading.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()
    val progressValue by viewModel.progressValue.collectAsState()
    val progressValue2 by viewModel.progressValue2.collectAsState()
    val showError by viewModel.showError.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val randomProgressColor: List<Color> = listOf(
        Color(0xFFF54D64),
        Color(0xFFFF8C4B)
    )
    val loadProgressColor: List<Color> = listOf(
        Color(0xFFFF0000),
        Color(0xFF0038FF)
    )

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(AppTheme.colors.controlGraphBlue)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 8.dp)
    ) {
        Row() {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = stringResource(R.string.back), tint = AppTheme.colors.systemGraphPrimary)
            }
        }

        Row(modifier = Modifier.padding(top = 40.dp, start = 14.dp, end = 14.dp)) {
            CustomProgressBar(
                title = stringResource(id = R.string.randomValue),
                gradientColors = randomProgressColor,
                backgroundIndicatorColor = Color(0xFF242424),
                downloadedPercentage = progressValue,
                viewClip = 7.dp,
                viewHeit = 11.dp,
                textSize = 22.sp
            )
        }
        Row(modifier = Modifier.padding(top = 12.dp, start = 14.dp, end = 14.dp)) {
            CustomProgressBar(
                title = stringResource(id = R.string.randomValue2),
                gradientColors = randomProgressColor,
                backgroundIndicatorColor = Color(0xFF242424),
                downloadedPercentage = progressValue2,
                viewClip = 7.dp,
                viewHeit = 11.dp,
                textSize = 22.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 28.dp, start = 14.dp, end = 14.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = viewModel::randomProgress,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .height(39.dp)
            ) {
                Text(text = stringResource(id = R.string.randomize), fontSize = 17.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TimeItem(name = stringResource(id = R.string.hours), value = timerHour)
            TimeItem(name = stringResource(id = R.string.minutes), value = timerMin, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
            TimeItem(name = stringResource(id = R.string.seconds), value = timerSecond)
        }
        Row(modifier = Modifier.padding(top = 25.dp, start = 14.dp, end = 14.dp)) {
            CustomProgressBar(
                title = stringResource(id = R.string.loadData),
                gradientColors = loadProgressColor,
                backgroundIndicatorColor = Color(0xFFFFFFFF),
                downloadedPercentage = progressLoading,
                viewClip = 5.dp,
                viewHeit = 20.dp,
                textSize = 16.sp
            )
        }
        if (!loadingState) {
            LazyRow(
                modifier = Modifier.padding(start = 13.dp, top = 40.dp)
            ) {
                items(raitingsList) {
                    RaitingListItem(it)
                }
            }
        }
    }
    if (showError) {
        Dialog(
            onDismissRequest = { }
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .background(AppTheme.colors.systemGraphOnPrimary)
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), horizontalArrangement = Arrangement.Center) {
                        Text(text = stringResource(id = R.string.error), fontSize = 16.sp, color = AppTheme.colors.colorTextAlert, style = AppTheme.typography.h2)
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(15.dp), horizontalArrangement = Arrangement.Center) {
                        Text(text = errorMessage, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 21.dp, top = 65.dp), horizontalArrangement = Arrangement.Center) {
                        Button(
                            modifier = Modifier
                                .width(151.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(31.dp),
                            onClick = viewModel::changeErrorVisible
                        ) {
                            Text(text = stringResource(id = com.example.presentation.base_ui.R.string.close), fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RaitingListItem(item: UserRaitings = UserRaitings(image = "https://www.dropbox.com/s/fkpioapbu3gqjj5/7.png?dl=1", title = "text 7 \ntext 7 \ntext 7")) {
    ConstraintLayout(modifier = Modifier.padding(start = 10.dp)) {
        val (card, stars) = createRefs()

        Card(
            modifier = Modifier
                .size(width = 256.dp, height = 90.dp)
                .clip(RoundedCornerShape(24.dp))
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, 8.6.dp)
                }
        ) {
            Row(
                modifier = Modifier
                    .background(Color(0xFF27292C))
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 20.dp, bottom = 20.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(item.image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 20.dp, bottom = 20.dp)
                ) {
                    Text(text = item.title, color = AppTheme.colors.systemTextOnPrimary)
                }
            }
        }
        Row(
            modifier = Modifier.constrainAs(stars) {
                start.linkTo(parent.start, 15.5.dp)
                top.linkTo(parent.top)
            }
        ) {
            repeat(5) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = stringResource(
                        id = R.string.star
                    ),
                    tint = Color(0xFFFFD600)
                )
            }
        }
    }
}

@Preview
@Composable
fun RaitingListItemPreview() {
    val item = UserRaitings(image = "https://www.dropbox.com/s/fkpioapbu3gqjj5/7.png?dl=1", title = "text 7 \ntext 7 \n  text 7")
    RaitingListItem(item)
}
