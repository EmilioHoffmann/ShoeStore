package com.udacity.shoestore.core.utils

import android.content.Context
import android.content.SharedPreferences

fun Context.saveSharedPreferenceString(key: String, value: String) {
    with(getShared().edit()) {
        this?.putString(key, value)
        apply()
    }
}

fun Context.saveSharedPreferenceBoolean(key: String, value: Boolean) {
    with(getShared().edit()) {
        this?.putBoolean(key, value)
        apply()
    }
}

fun Context.getSharedPreferenceBoolean(key: String, defaultValue: Boolean = false): Boolean {
    return getShared().getBoolean(key, defaultValue)
}

fun Context.getShared(): SharedPreferences {
    return getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
}

const val SHARED_PREFERENCE_FILE_NAME = "SHOE_STORE_SHARED_PREF"
const val SHARED_PREFERENCE_KEEP_LOGGED = "SHARED_PREFERENCE_KEEP_LOGGED"
