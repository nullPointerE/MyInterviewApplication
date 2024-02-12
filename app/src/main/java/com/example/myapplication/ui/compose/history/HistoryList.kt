package com.example.myapplication.ui.compose.history

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.ConversionResult

@Composable
fun HistoryList(
    modifier: Modifier = Modifier,
    resultList: State<List<ConversionResult>>,
    onClose: (ConversionResult) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(resultList.value.size) { index ->
            HistoryItem(message = resultList.value[index].message,
                onClose = {
                    onClose(resultList.value[index])
                })
        }
    }
}