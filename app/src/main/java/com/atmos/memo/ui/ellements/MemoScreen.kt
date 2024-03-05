package com.atmos.memo.ui.ellements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.atmos.memo.ui.theme.MemoTheme

@Composable
fun MemoScreen(screenContent: @Composable () -> Unit) {
    MemoTheme {
        Surface(
            Modifier
                .fillMaxSize()
        )
        {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                screenContent()
            }
        }
    }
}