package com.cblanco.rickandmortychars.core.data.disk

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.cblanco.rickandmortychars.core.di.AppModule
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class DiskDataStoreEncrypted @Inject constructor(
    @Named(AppModule.nameApp) private val nameApp: String,
    context: Context
) {
    private val fileName = "$nameApp.preferences"
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private var preferences = EncryptedSharedPreferences.create(fileName, masterKeyAlias, context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
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