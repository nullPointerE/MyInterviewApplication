package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.compose.ScreenEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {
    private val _sharedState = MutableStateFlow(0)
    val sharedState = _sharedState.asStateFlow()

    private val _screenEvent = MutableSharedFlow<ScreenEvents>()
    val screenEvents = _screenEvent.asSharedFlow()

    fun updateState() {
        _sharedState.value++
    }

}