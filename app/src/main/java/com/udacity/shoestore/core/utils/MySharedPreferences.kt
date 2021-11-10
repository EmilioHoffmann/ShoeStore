package com.udacity.shoestore.core.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment

fun Fragment.saveSharedPreferenceString(key: String, value: String) {
    with(getShared().edit()) {
        this?.putString(key, value)
        apply()
    }
}

fun Fragment.saveSharedPreferenceBoolean(key: String, value: Boolean) {
    with(getShared().edit()) {
        this?.putBoolean(key, value)
        apply()
    }
}

fun Fragment.getSharedPreferenceBoolean(key: String, defaultValue: Boolean = false): Boolean {
    return getShared().getBoolean(key, defaultValue)
}

fun Fragment.getShared(): SharedPreferences {
    return requireContext().getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
}

const val SHARED_PREFERENCE_FILE_NAME = "SHOE_STORE_SHARED_PREF"
const val SHARED_PREFERENCE_KEEP_LOGGED = "SHARED_PREFERENCE_KEEP_LOGGED"
