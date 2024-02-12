package com.example.myapplication.ui.compose.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.example.myapplication.data.ConversionResult
import com.example.myapplication.repository.ConvertRepository

@Composable
fun HistoryScreen(
    list: State<List<ConversionResult>>,
    modifier: Modifier = Modifier,
    onClose: (ConversionResult) -> Unit
) {
    HistoryList(resultList = list, onClose = onClose)
}