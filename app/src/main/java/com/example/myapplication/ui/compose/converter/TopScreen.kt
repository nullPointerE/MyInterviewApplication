package com.example.myapplication.ui.compose.converter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.myapplication.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    savedResult: (String) -> Unit
) {
    val selectedConversion: MutableState<Conversion?> = rememberSaveable {
        mutableStateOf(null)
    }
    val inputText: MutableState<String> = rememberSaveable {
        mutableStateOf("")
    }

    val typedValue = rememberSaveable {
        mutableStateOf("0.0")
    }
    ConversionMenu(list = list) {
        selectedConversion.value = it
        typedValue.value = "0.0"
    }
    selectedConversion.value?.let { conversion ->
        InputBlock(
            conversion = conversion, inputText = inputText
        ) {
            typedValue.value = it
        }
    }
    if (typedValue.value != "0.0") {
        val inputDouble = typedValue.value.toDouble()
        val multiply = inputDouble * selectedConversion.value!!.convertRatio
        val decimalFormat = DecimalFormat("#.###")
        decimalFormat.roundingMode = RoundingMode.DOWN
        val roundResult = decimalFormat.format(multiply)
        val message =
            "${typedValue.value} ${selectedConversion.value!!.convertFrom} => $roundResult ${selectedConversion.value!!.convertTo}"
        ResultBlock(roundResult = message)
        savedResult(message)
    }
}