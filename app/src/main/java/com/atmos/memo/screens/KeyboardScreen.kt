package com.atmos.memo.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.atmos.memo.ui.theme.MemoTheme

@Composable
fun KeyboardScreen(navController: NavController) {
    MemoTheme {
        Surface(
            Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Text(text = "222")
        }
    }
}