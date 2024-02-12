package com.example.myapplication.ui.compose.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultBlock(
    modifier: Modifier = Modifier,
    roundResult: String) {
    Card(modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)) {
        Column(modifier = modifier.padding(10.dp)) {
            Text(text = roundResult)
        }
    }
}