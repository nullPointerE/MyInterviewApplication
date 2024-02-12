package com.example.myapplication.ui.compose.converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.myapplication.data.Conversion
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionMenu(
    list: List<Conversion>,
    modifier: Modifier = Modifier,
    convert: (Conversion) -> Unit,
) {
    val defaultString = stringResource(id = R.string.drop_down_header)
    var displayText by rememberSaveable {
        mutableStateOf(
            defaultString
        )
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    var dropDownExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    val icon = if (dropDownExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    Column {
        OutlinedTextField(
            value = displayText,
            onValueChange = { displayText = it },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            label = { Text(stringResource(id = R.string.conversion_type)) },
            trailingIcon = {
                Icon(icon, contentDescription = "icon",
                    modifier.clickable {
                        dropDownExpanded = !dropDownExpanded
                    })
            },
            readOnly = true
        )

        DropdownMenu(
            expanded = dropDownExpanded,
            onDismissRequest = { dropDownExpanded = false },
            modifier = modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach { conversion ->
                DropdownMenuItem(
                    onClick = {
                        convert(conversion)
                        displayText = conversion.description
                        dropDownExpanded = false
                    },
                    text = {
                        Text(
                            text = conversion.description,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }
    }
}

