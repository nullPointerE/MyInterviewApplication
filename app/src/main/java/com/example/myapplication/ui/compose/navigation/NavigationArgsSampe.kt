package com.example.myapplication.ui.compose.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun NavigationSample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            Screen1(onNavigationScreen2 = {
                navController.navigate("screen2/$it")
            })
        }
        composable(
            route = "screen2/{my_param}",
            arguments = listOf(
                navArgument(name = "my_param") {
                    type = NavType.StringType
                }
            )
        ) {
            val param = it.arguments?.getString("my_param") ?: ""
            Screen2(param = param)
        }
    }
}

@Composable
private fun Screen1(onNavigationScreen2: (String) -> Unit){
    Button(onClick = { onNavigationScreen2("hello")}) {
        Text(text = "click")
    }
}

@Composable
private fun Screen2(param: String){
    Text(text = param)
}