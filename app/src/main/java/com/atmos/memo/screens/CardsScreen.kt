package com.atmos.memo.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.atmos.memo.R
import com.atmos.memo.ui.ellements.CustomRow
import com.atmos.memo.ui.ellements.MemoScreen
import com.atmos.memo.utils.MyPreview
import com.atmos.memo.utils.loadListFromSharedPreferences

@Composable
fun CardsScreen(navController: NavController) {
    val context = LocalContext.current
    val rowsState: List<Pair<String, String>> = loadListFromSharedPreferences(context)

    if (rowsState.size < 4) {
        navController.navigate("HomeScreen")
        Toast.makeText(context, "Select a file with 4 elements", Toast.LENGTH_SHORT).show()
        return
    }

    val randomElements = remember { mutableStateOf(rowsState.shuffled().take(4)) }
    val isStart = remember { mutableStateOf(true) }
    val questionIndex = remember { mutableIntStateOf(0) }
    val isSecondFirst = remember { mutableStateOf(false) } // Step 1

    if (isStart.value) {
        randomElements.value = rowsState.shuffled().take(4)
        questionIndex.intValue = (0..3).random()
        isStart.value = false
    }


    MemoScreen {
        CustomRow(
            title = stringResource(R.string.cards),
            iconResource = R.drawable.baseline_home_24,
            buttonText = "Home"
        ) {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            ) {
                Text(
                    text = if (isSecondFirst.value) {
                        randomElements.value[questionIndex.intValue].first
                    } else {
                        randomElements.value[questionIndex.intValue].second
                    },
                    fontSize = 30.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Swap places")
                Switch(
                    checked = isSecondFirst.value,
                    modifier = Modifier,
                    onCheckedChange = {
                        isSecondFirst.value = it
                    },
                    thumbContent = if (isSecondFirst.value) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    } else {
                        null
                    }
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                for (i in 0 until 4) {
                    val buttonText = if (isSecondFirst.value) {
                        randomElements.value[i].second
                    } else {
                        randomElements.value[i].first
                    }
                    Button(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .weight(0.4f),
                        onClick = {
                            val correctAnswer = if (isSecondFirst.value) {
                                randomElements.value[questionIndex.intValue].second
                            } else {
                                randomElements.value[questionIndex.intValue].first
                            }
                            if (buttonText == correctAnswer) {
                                isStart.value = true

                            }
                        }
                    ) {
                        Text(buttonText)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun CardScreenPreview() {
    MyPreview(screenContent = { navController ->
        CardsScreen(navController = navController)
    })
}