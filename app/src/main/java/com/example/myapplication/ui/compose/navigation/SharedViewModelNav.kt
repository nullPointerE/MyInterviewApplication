package com.example.myapplication.ui.compose.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.viewmodel.SharedViewModel

val LocalSnackbarHostState = compositionLocalOf {
    SnackbarHostState()
}

@Composable
fun SharedViewModelNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        navigation(
            startDestination = "personal_detail",
            route = "home",
        ) {
            composable("personal_detail") { navBackStackEntry ->
                LocalContext.current
                val viewModel = navBackStackEntry.sharedViewModel<SharedViewModel>(navController)
                val state by viewModel.sharedState.collectAsState()
                PersonalDetailScreen(sharedState = state,
                    onNavigate = {
                        viewModel.updateState()
                        navController.navigate("terms_and_conditions")
                    })
            }


            composable("terms_and_conditions") { navBackStackEntry ->
                val viewModel = navBackStackEntry.sharedViewModel<SharedViewModel>(navController)
                val state by viewModel.sharedState.collectAsState()
                PersonalDetailScreen(sharedState = state,
                    onNavigate = {
                        viewModel.updateState()
                        navController.navigate("other_screen") {
                            popUpTo("home")
                        }
                    })
            }
        }
    }

}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

@Composable
fun AppRoot() {
    val snackBarHostState = LocalSnackbarHostState.current
    CompositionLocalProvider(LocalSnackbarHostState provides snackBarHostState) {
        
    }
}

@Composable
private fun PersonalDetailScreen(sharedState: Int, onNavigate: () -> Unit) {
    Button(onClick = onNavigate) {
        Text("click")
    }
}

@Composable
private fun TermsAndConditions(sharedState: Int, onNavigate: () -> Unit) {
    Button(onClick = onNavigate) {
        Text("click")
    }
}