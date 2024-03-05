package com.atmos.memo.utils

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import org.json.JSONArray
import org.json.JSONObject

fun saveToJsonFile(
    context: Context,
    uri: Uri,
    rowsState: List<Pair<MutableState<TextFieldValue>, MutableState<TextFieldValue>>>
) {

    try {
        val jsonArray = JSONArray()
        for (row in rowsState) {
            val jsonObject = JSONObject().apply {
                put(row.first.value.text, row.second.value.text)
            }
            jsonArray.put(jsonObject)
        }

        context.contentResolver.openOutputStream(uri)?.use { outputStream ->
            outputStream.write(jsonArray.toString().toByteArray())
        }
        val transformedList: List<Pair<String, String>> = rowsState.map { (firstTextFieldState, secondTextFieldState) ->
            Pair(firstTextFieldState.value.text, secondTextFieldState.value.text)
        }
        saveListToSharedPreferences(context, transformedList)

    } catch (e: Exception) {
        e.printStackTrace()
    }
}