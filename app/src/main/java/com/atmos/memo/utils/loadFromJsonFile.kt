package com.atmos.memo.utils

import android.content.Context
import android.net.Uri
import org.json.JSONArray


fun loadFromJsonFile(
    context: Context,
    uri: Uri
): List<Pair<String, String>> {
    val rowsState = mutableListOf<Pair<String, String>>()

    try {
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val key = jsonObject.keys().next()
                val value = jsonObject.getString(key)

                rowsState.add(key to value)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        // Handle error loading file
        // You can show a Snackbar or Toast message here
    }
    saveListToSharedPreferences(context, rowsState)
    return rowsState
}

