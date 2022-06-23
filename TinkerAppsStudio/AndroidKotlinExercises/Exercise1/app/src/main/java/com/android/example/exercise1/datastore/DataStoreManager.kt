package com.android.example.exercise1.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class DataStoreManager(val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "newDatastore")

    companion object{
        val Score = intPreferencesKey("Score")

    }

    suspend fun savetoDataStore(score: Int)
    {
        context.dataStore.edit {
          it[Score] = score?:0
        }
    }

   val scoreFlow: Flow<Int?> = context.dataStore.data.map { it[Score] }
}


