package com.example.second.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.second.models.UserRaitings
import com.example.second.usecase.GetRaitingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val grtRaitingsUseCase: GetRaitingsUseCase
) : ViewModel() {

    private val _raitingList = MutableStateFlow(emptyList<UserRaitings>())
    val raitingList = _raitingList.asStateFlow()
    private val _showError = MutableStateFlow(false)
    val showError = _showError.asStateFlow()
    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()
    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()
    private val _progressValue = MutableStateFlow(0f)
    val progressValue = _progressValue.asStateFlow()
    private val _progressValue2 = MutableStateFlow(0f)
    val progressValue2 = _progressValue2.asStateFlow()
    private val _progressLoading = MutableStateFlow(0f)
    val progressLoading = _progressLoading.asStateFlow()
    private val _timerHour = MutableStateFlow("01")
    val timerHour = _timerHour.asStateFlow()
    private val _timerMin = MutableStateFlow("00")
    val timerMin = _timerMin.asStateFlow()
    private val _timerSecond = MutableStateFlow("00")
    val timerSecond = _timerSecond.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _loadingState.value = true
                loading()
                grtRaitingsUseCase.getRaitings().collectLatest {
                    _raitingList.value = it
                }
                _progressLoading.value
            } catch (e: Exception) {
                _errorMessage.value = e.message.toString()
                _showError.value = true
            } finally {
                _loadingState.value = false
                _progressLoading.value = 1f
            }
        }
        countdown()
    }

    fun countdown() {
        val calendar = Calendar.getInstance()
        val today = calendar.get(Calendar.DAY_OF_YEAR)
        calendar.set(Calendar.HOUR_OF_DAY, 1)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val hourFormat = SimpleDateFormat("HH")
        val minFormat = SimpleDateFormat("mm")
        val secondFormat = SimpleDateFormat("ss")
        viewModelScope.launch(Dispatchers.IO) {
            while (today == calendar.get(Calendar.DAY_OF_YEAR)) {
                delay(1000)
                calendar.add(Calendar.SECOND, -1)

                _timerHour.value = hourFormat.format(calendar.time)
                _timerMin.value = minFormat.format(calendar.time)
                _timerSecond.value = secondFormat.format(calendar.time)
            }
        }
    }

    fun randomProgress() {
        val random = (5 until 26).random()
        val random2 = (5 until 26).random()
        val column = 1f / random
        val column2 = 1f / random2

        viewModelScope.launch(Dispatchers.IO) {
            for (i in 0 until random) {
                _progressValue.value = _progressValue.value + column
                delay(1000)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            for (i in 0 until random2) {
                _progressValue2.value = _progressValue2.value + column2
                delay(1000)
            }
        }
    }

    private fun loading() {
        viewModelScope.launch {
            while (_loadingState.value) {
                _progressLoading.value = _progressLoading.value + 0.05f
                delay(1000)
            }
        }
    }
    fun changeErrorVisible() {
        _showError.value = false
    }
}
