package com.atmos.memo.screens

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.atmos.memo.R
import com.atmos.memo.ui.ellements.CustomRow
import com.atmos.memo.ui.ellements.MemoScreen
import com.atmos.memo.ui.ellements.TerminDescriptionColumn
import com.atmos.memo.utils.loadFromJsonFile
import com.atmos.memo.utils.saveToJsonFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FileEdit(navController: NavController) {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
    val formattedDateTime = currentDateTime.format(formatter)
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // State for storing list of text fields
    val rowsState =
        remember { mutableStateListOf<Pair<MutableState<TextFieldValue>, MutableState<TextFieldValue>>>() }

    // State for storing indexes of rows to remove
    val rowsToRemove = remember { mutableStateListOf<Int>() }
    val saveFileLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("*/*")) { uri ->
            uri?.let {
                // Handle file selection
                coroutineScope.launch(Dispatchers.IO) {
                    saveToJsonFile(context, it, rowsState)
                }
            }
        }
    val pickFileLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                // Handle file selection
                coroutineScope.launch(Dispatchers.IO) {
                    val loadedRows = loadFromJsonFile(context, it)
                    rowsState.clear()
                    rowsState.addAll(loadedRows.map { pair ->
                        mutableStateOf(TextFieldValue(pair.first)) to mutableStateOf(
                            TextFieldValue(
                                pair.second
                            )
                        )
                    })
                }
            }
        }

    MemoScreen {
        CustomRow(
            title = "Current",
            iconResource = R.drawable.baseline_arrow_back_24,
            buttonText = "back"
        ) { navController.popBackStack() }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextButton(
                onClick = {
                    val leftTextFieldState = mutableStateOf(TextFieldValue())
                    val rightTextFieldState = mutableStateOf(TextFieldValue())
                    rowsState.add(leftTextFieldState to rightTextFieldState)
                },
                modifier = Modifier
                    .weight(0.2f)
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = "Add",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
            Spacer(modifier = Modifier.weight(0.15f))
            TextButton(
                onClick = {
                    saveFileLauncher.launch("$formattedDateTime.json")
                },
                modifier = Modifier
                    .weight(0.2f)
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_save_24),
                    contentDescription = "Save",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
            Spacer(modifier = Modifier.weight(0.15f))
            TextButton(
                onClick = {
                    pickFileLauncher.launch("*/*")
                },
                modifier = Modifier
                    .weight(0.2f)
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_file_present_24),
                    contentDescription = "Load",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
        }

        // Lazy column with added rows
        LazyColumn {
            itemsIndexed(rowsState) { index, item ->
                val (leftTextFieldState, rightTextFieldState) = item
                TerminDescriptionColumn(
                    leftTextFieldState,
                    rightTextFieldState,
                    onDelete = { rowsToRemove.add(index) }
                )
            }
        }
    }

    // Remove items from rowsState that should be deleted
    rowsToRemove.forEach { index ->
        rowsState.removeAt(index)
    }
    rowsToRemove.clear()
}
