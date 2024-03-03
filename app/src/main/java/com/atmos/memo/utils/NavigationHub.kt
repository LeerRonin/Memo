package com.atmos.memo.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.atmos.memo.screens.CardsScreen
import com.atmos.memo.screens.HomeScreen
import com.atmos.memo.screens.KeyboardScreen
import com.atmos.memo.screens.SettingScreen

@Composable
fun NavigationHub() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "HomeScreen") {
        composable("HomeScreen") { HomeScreen(navController) }
        composable("SettingScreen") { SettingScreen(navController) }
        composable("CardsScreen") { CardsScreen(navController) }
        composable("KeyboardScreen") { KeyboardScreen(navController) }
    }
}