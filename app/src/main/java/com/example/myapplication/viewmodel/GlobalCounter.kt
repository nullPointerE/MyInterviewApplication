package com.example.myapplication.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object GlobalCounter {
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    fun increase() {
        _count.value++
    }
}