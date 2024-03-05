package com.atmos.memo.utils

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray

private const val SHARED_PREF_NAME = "my_shared_pref"
private const val KEY_JSON_LIST = "json_list"

fun saveListToSharedPreferences(
    context: Context,
    list: List<Pair<String, String>>
) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val jsonArray = JSONArray()

    for (pair in list) {
        val jsonObject = JSONArray()
        jsonObject.put(pair.first)
        jsonObject.put(pair.second)
        jsonArray.put(jsonObject)
    }

    editor.putString(KEY_JSON_LIST, jsonArray.toString())
    editor.apply()
}


fun loadListFromSharedPreferences(context: Context): List<Pair<String, String>> {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    val jsonString = sharedPreferences.getString(KEY_JSON_LIST, null)

    val rowsState = mutableListOf<Pair<String, String>>()

    if (!jsonString.isNullOrEmpty()) {
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONArray(i)
            val key = jsonObject.getString(0)
            val value = jsonObject.getString(1)

            rowsState.add(key to value)
        }
    }

    return rowsState
}