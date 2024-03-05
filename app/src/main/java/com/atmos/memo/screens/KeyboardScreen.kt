package com.atmos.memo.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.atmos.memo.R
import com.atmos.memo.ui.ellements.CustomRow
import com.atmos.memo.ui.ellements.MemoScreen
import com.atmos.memo.utils.MyPreview
import com.atmos.memo.utils.loadListFromSharedPreferences

@Composable
fun KeyboardScreen(navController: NavController) {
    val context = LocalContext.current
    val rowsState: List<Pair<String, String>> = loadListFromSharedPreferences(context)

    if (rowsState.isEmpty()) {
        navController.navigate("HomeScreen")
        Toast.makeText(context, "Select a file with 1 elements", Toast.LENGTH_SHORT).show()
        return
    }

    val randomPair = remember { mutableStateOf(rowsState.random()) }
    val isStart = remember { mutableStateOf(true) }
    val isSecondFirst = remember { mutableStateOf(false) }

    val question = remember { mutableStateOf("")
    }
    val answer = remember { mutableStateOf("") }

    if (isStart.value) {
        randomPair.value = rowsState.random()
        isStart.value = false
    }
    if (isSecondFirst.value) {
        question.value = randomPair.value.first
        answer.value = randomPair.value.second
    } else {
        question.value = randomPair.value.second
        answer.value = randomPair.value.first
    }

    val enteredText = remember { mutableStateOf("") }

    MemoScreen {
        CustomRow(
            title = stringResource(R.string.keyboard),
            iconResource = R.drawable.baseline_home_24,
            buttonText = "Home"
        ) {
            navController.popBackStack()
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
        ) {
            Text(
                text = question.value,
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

        TextField(
            value = enteredText.value,
            onValueChange = { text ->
                enteredText.value = text
                if (text == answer.value) {
                    enteredText.value = ""
                    isStart.value = true
                }
            }
        )
    }
}

@Preview
@Composable
fun KeyboardPreview() {
    MyPreview(screenContent = { navController ->
        KeyboardScreen(navController = navController)
    })
}
