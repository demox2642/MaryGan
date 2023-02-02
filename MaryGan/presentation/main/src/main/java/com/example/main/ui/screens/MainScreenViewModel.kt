package com.example.main.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel@Inject constructor() : ViewModel() {
    private val _lattieViewState = MutableStateFlow(false)
    val lattieViewState = _lattieViewState.asStateFlow()
    private val _animationPlayingState = MutableStateFlow(false)
    val animationPlayingState = _animationPlayingState.asStateFlow()
    private val _dialogShowState = MutableStateFlow(false)
    val dialogShowState = _dialogShowState.asStateFlow()
    private val _loadingProgress = MutableStateFlow(0.0f)
    val loadingProgress = _loadingProgress.asStateFlow()

    private val seconds = 14
    private val progressCount = 1f / seconds

    private val _loadingSecond = MutableStateFlow(1)

    private var timerJob: Job? = null

    private var jobRun: Boolean = true

    fun stopTimer() {
        jobRun = false
        timerJob?.cancel()
    }

    init {
        startTimer()
    }

    fun startTimer() {
        jobRun = true
        timerJob?.cancel()
        timerJob = viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                while (jobRun) {
                    for (i in _loadingSecond.value..seconds) {
                        delay(1000L)
                        _loadingSecond.value = _loadingSecond.value + 1
                        _loadingProgress.value = _loadingProgress.value + progressCount
                        if (_loadingSecond.value == seconds){
                            timerJob?.cancel()
                        }
                    }
                }
            }
        }
    }

    fun showAnimationView() {
        Log.e("MainScreenVM", "showAnimationView() ")
        _lattieViewState.value = _lattieViewState.value.not()
    }

    fun playAnimation() {
        _animationPlayingState.value = true
    }

    fun stopAnimation() {
        _animationPlayingState.value = false
    }

    fun changeVisibleDialog(){
        _dialogShowState.value = _dialogShowState.value.not()
    }
}
