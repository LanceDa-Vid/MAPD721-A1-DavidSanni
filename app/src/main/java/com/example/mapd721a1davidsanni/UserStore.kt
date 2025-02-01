package com.example.mapd721a1davidsanni

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore(private val context: Context) {
    companion object {
        private val Context.dataStore by preferencesDataStore("userPrefs")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_COURSE_KEY = stringPreferencesKey("user_course")
    }

    val getAccessID: Flow<String> = context.dataStore.data.map { it[USER_ID_KEY] ?: "" }
    val getAccessName: Flow<String> = context.dataStore.data.map { it[USER_NAME_KEY] ?: "" }
    val getAccessCourse: Flow<String> = context.dataStore.data.map { it[USER_COURSE_KEY] ?: "" }

    suspend fun saveUserID(id: String) = context.dataStore.edit { it[USER_ID_KEY] = id }
    suspend fun saveUsername(username: String) = context.dataStore.edit { it[USER_NAME_KEY] = username }
    suspend fun saveCourse(course: String) = context.dataStore.edit { it[USER_COURSE_KEY] = course }
    suspend fun clearData() = context.dataStore.edit { it.clear() }
}