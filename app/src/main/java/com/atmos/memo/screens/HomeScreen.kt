package com.atmos.memo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.atmos.memo.R
import com.atmos.memo.ui.theme.MemoTheme

@Composable
fun HomeScreen(navController : NavController){
    MemoTheme {
        Surface (
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            stringResource(R.string.app_name),
                            textAlign = TextAlign.Center,
                            fontSize = 40.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        TextButton(
                            onClick = { navController.navigate("SettingScreen") },
                            modifier = Modifier.size(80.dp),
                        ) {
                            Image(
                                painter = painterResource(R.drawable.baseline_settings_24),
                                contentDescription = "Cards",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )

                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly

                    ) {
                        TextButton(
                            onClick = { navController.navigate("CardsScreen") },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                modifier = Modifier.size(100.dp),
                                painter = painterResource(R.drawable.playing_cards),
                                contentDescription = "Cards",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )

                        }
                        TextButton(
                            onClick = { navController.navigate("KeyboardScreen") },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                modifier = Modifier.size(100.dp),
                                painter = painterResource(R.drawable.baseline_keyboard_24),
                                contentDescription = "Keyboard",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )
                        }
                    }
                }
            }
        }
    }


@Preview
@Composable
fun MyPreview(){
    val navController = rememberNavController()
    HomeScreen(navController)
}
