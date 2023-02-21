package com.cblanco.rickandmortychars.core.data.disk

import android.content.Context
import com.cblanco.rickandmortychars.core.di.AppModule
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class DiskDataStore @Inject constructor(
    @Named(AppModule.nameApp) private val nameApp: String,
    context: Context
) {
    private val fileName = "$nameApp.preferences"
    private var preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    private var editor = preferences.edit()

    fun putString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String = preferences.getString(key, "") ?: ""

    fun putStringSet(key: String, value: HashSet<String>) {
        value.map { it }
        editor.putStringSet(key, HashSet(value.map { it }))
        editor.apply()
    }

    fun getStringSet(key: String): Set<String> {
        val setStrings = preferences.getStringSet(key, HashSet())
        return HashSet(setStrings?.map { it })
    }

    fun clear(key: String) {
        editor.remove(key)
        editor.apply()
    }
}