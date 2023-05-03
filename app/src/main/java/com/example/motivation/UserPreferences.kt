package com.example.motivation

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun saveUser(user: String) {
        preferences.edit().putString("user", user).apply()
    }

    fun getUser(key: String): String {
        return preferences.getString(key, "GUEST") ?: "NOT_FOUND_USER"
    }

}