package com.example.myapplication.viewmodel

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Conversion
import com.example.myapplication.data.ConversionResult
import com.example.myapplication.repository.ConvertRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ConvertViewModel(private val convertRepository: ConvertRepository) : ViewModel() {

    fun getConversions() = listOf(
        Conversion(1, "Pounds -> Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms -> Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards -> Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters -> Yards", "m", "yd", 1.09361),
        Conversion(5, "Miles -> Kilometers", "mi", "km", 1.60934),
        Conversion(6, "Kilometers -> Miles", "km", "mi", 0.621371)
    )

    val results = convertRepository.getResults()

    fun addResult(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            convertRepository.insertResult(
                ConversionResult(0, message)
            )
        }
    }

    fun deleteResult(conversionResult: ConversionResult) {
        viewModelScope.launch(Dispatchers.IO) {
            convertRepository.deleteResult(conversionResult)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            convertRepository.deleteAll()
        }
    }

    private val stateFlow = MutableStateFlow(0)
}

@Composable
fun Main() {
    val countDown = flow<Int> {
        var number = 10
        while (number >= 0) {
            emit(number)
            delay(1000)
            number--
        }
    }
    val num = countDown.collectAsState(initial = 10)
    Text(text = num.value.toString(), modifier = Modifier)
}