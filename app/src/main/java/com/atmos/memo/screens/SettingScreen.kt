package com.atmos.memo.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.atmos.memo.R
import com.atmos.memo.ui.ellements.CustomRow
import com.atmos.memo.ui.ellements.MemoScreen
import androidx.compose.ui.tooling.preview.Preview as SettingPreview

@Composable
fun SettingScreen(navController: NavController) {
    MemoScreen {
        CustomRow(stringResource(R.string.setting), R.drawable.baseline_home_24, "Home") {
            navController.popBackStack()
        }
        LazyColumn(
            Modifier
                .padding(20.dp)
        ) {
            item {
                Button(
                    onClick = { navController.navigate("FileEdit") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(bottom = 10.dp)
                ) {
                    Text(text = "Редактор файлов")
                }
            }
        }
    }
}

@SettingPreview
@Composable
fun SettingPreview() {
    val navController = rememberNavController()
    SettingScreen(navController)
}
