package com.example.main.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.example.base_ui.theme.AppTheme
import com.example.presentation.main.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import uiview.CustomAlertDialog
import uiview.CustomProgressBar

@Composable
fun MainScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<MainScreenViewModel>()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(AppTheme.colors.systemBackgroundPrimary)

    val progress by viewModel.loadingProgress.collectAsState()
    val showAnimationViewState by viewModel.lattieViewState.collectAsState()
    val playAnimation by viewModel.animationPlayingState.collectAsState()
    val dialogShowState by viewModel.dialogShowState.collectAsState()

    val gradientColors: List<Color> = listOf(
        AppTheme.colors.systemGraphOnPrimary,
        AppTheme.colors.controlGraphBlue,
        AppTheme.colors.controlGraphBlueDark
    )

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_START) {
            viewModel.startTimer()
        } else if (event == Lifecycle.Event.ON_STOP) {
            Lifecycle.Event.ON_STOP
            viewModel.stopTimer()
        }
    }

    lifecycleOwner.lifecycle.addObserver(observer)
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState).padding(bottom = 8.dp, start = 14.dp, end = 14.dp)) {
        Row(modifier = Modifier.padding(top = 40.dp)) {
            CustomProgressBar(
                title = stringResource(id = R.string.loading),
                gradientColors = gradientColors,
                downloadedPercentage = progress,
                viewClip = 5.dp,
                viewHeit = 30.dp,
                textSize = 16.sp
            )
        }
        Spacer(Modifier.height(30.dp))
        LottieAnimationBox(
            showAnimationViewState = showAnimationViewState,
            playAnimation = playAnimation,
            stopButtonClick = viewModel::stopAnimation,
            playButtonClick = viewModel::playAnimation,
            showHidButtonCluck = viewModel::showAnimationView
        )
        Spacer(Modifier.height(60.dp))
        Row() {
            Button(
                onClick = viewModel::changeVisibleDialog,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = stringResource(id = R.string.showAlert), fontSize = 17.sp)
            }
        }
        Spacer(Modifier.height(185.dp))
        Row(modifier = Modifier.fillMaxHeight()) {
            Button(
                onClick = { navHostController.navigate("second") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 36.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = stringResource(id = R.string.secondScreen), fontSize = 17.sp)
            }
        }

        if (dialogShowState) {
            Dialog(
                onDismissRequest = { }
            ) {
                CustomAlertDialog(closeDialog = viewModel::changeVisibleDialog)
            }
        }
    }
}

@Composable
fun LottieAnimationBox(
    showAnimationViewState: Boolean,
    playAnimation: Boolean,
    stopButtonClick: () -> Unit,
    playButtonClick: () -> Unit,
    showHidButtonCluck: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
    ) {
        Column(
            modifier = Modifier.defaultMinSize(minWidth = 100.dp).weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showAnimationViewState) {
                PlayerPageLottieAnimation(playAnimation)
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 14.dp, end = 14.dp)
                .defaultMinSize(minHeight = 160.dp)
                .weight(3f)

        ) {
            Row() {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    shape = RoundedCornerShape(12.dp),
                    onClick = playButtonClick
                ) {
                    Text(text = stringResource(id = R.string.startAnim), fontSize = 11.sp)
                }
            }
            Spacer(modifier = Modifier.height(9.dp))
            Row() {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    shape = RoundedCornerShape(12.dp),
                    onClick = stopButtonClick
                ) {
                    Text(text = stringResource(id = R.string.stoptAnim), fontSize = 11.sp)
                }
            }
            Spacer(modifier = Modifier.height(9.dp))
            Row() {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    shape = RoundedCornerShape(12.dp),
                    onClick = showHidButtonCluck
                ) {
                    Text(text = stringResource(id = R.string.hideShowAnim), fontSize = 10.sp, maxLines = 1)
                }
            }
        }
        Column(
            modifier = Modifier
                .defaultMinSize(minWidth = 100.dp)
                .weight(1f)
        ) {
        }
    }
}

@Composable
private fun PlayerPageLottieAnimation(
    isLottiePlaying: Boolean
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.animation)
    )

    val lottieAnimation by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLottiePlaying,
        restartOnPlay = false

    )
    LottieAnimation(
        composition,
        lottieAnimation,
        modifier = Modifier.size(100.dp)
    )
}
