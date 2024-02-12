package com.example.myapplication.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.compose.converter.TopScreen
import com.example.myapplication.ui.compose.history.HistoryScreen
import com.example.myapplication.viewmodel.ConvertViewModel
import com.example.myapplication.viewmodel.ConvertViewModelFactory

@Composable
fun BaseScreen(
    factory: ConvertViewModelFactory,
    modifier: Modifier = Modifier,
    convertViewModel: ConvertViewModel = viewModel(factory = factory),
) {
    val list = convertViewModel.getConversions()
    val historyList = convertViewModel
        .results
        .collectAsState(initial = emptyList())

    Column(modifier.padding(30.dp)) {
        TopScreen(list) { message ->
            convertViewModel.addResult(message)
        }
        Spacer(modifier = modifier.height(20.dp))
        HistoryScreen(historyList){
            convertViewModel.deleteResult(it)
        }
    }

}