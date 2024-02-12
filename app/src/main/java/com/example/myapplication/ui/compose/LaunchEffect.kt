package com.example.myapplication.ui.compose

import androidx.compose.animation.core.Animatable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.myapplication.viewmodel.SharedViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


sealed class ScreenEvents {
    data class ShowSnackBar(val message: String) : ScreenEvents()
    data class Navigate(val route: String) : ScreenEvents()
}

@Composable
fun showSnackBar(viewModel: SharedViewModel) {
    // show only the fist time composable is created
    LaunchedEffect(key1 = true) {
        viewModel.screenEvents.collect { screenEvent ->
            when (screenEvent) {
                is ScreenEvents.ShowSnackBar -> {}
                is ScreenEvents.Navigate -> {}
                else -> {
                }
            }
        }
    }
}

@Composable
fun animate(counter: Int) {
    val animatable = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = counter){
        animatable.animateTo(counter.toFloat())
    }
}

@Composable
fun RememberCoroutineScope(){
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
            delay(1000L)
            print("")
        }
    }){
    }
}

@Composable
fun RememberUpdateState(timeout: () -> Unit){
    val updatedState by rememberUpdatedState(newValue = timeout)
    LaunchedEffect(key1 = true){
        updatedState()
    }
}

@Composable
fun DisposableEffectDemo(){
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner){
        val observer = LifecycleEventObserver { _, event ->
            if(event == Lifecycle.Event.ON_CREATE){

            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

}

