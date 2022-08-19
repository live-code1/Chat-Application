package com.sample.chatapplication.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils

class SharedPrefsUtils {

    internal var PRIVATE_MODE = 0
    /**
     * A pack of helpful getter and setter methods for reading/writing to [SharedPreferences].
     */
    internal var pref: SharedPreferences? = null
    internal var editor: SharedPreferences.Editor? = null
    internal var _context: Context? = null

    companion object {

        /**
         * Helper method to retrieve a String value from [SharedPreferences].
         *
         * @param context      a [Context] object.
         * @param key          is the key of the value
         * @param defaultValue A default to return if the value could not be read.
         * @return The value from shared preferences, or the provided default.
         */
        fun getStringPreference(context: Context?, key: String, defaultValue: String): String? {
            var value: String? = defaultValue
            if (context != null) {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences != null) {
                    value = preferences.getString(key, defaultValue)
                }
            }
            return value
        }

        /**
         * Helper method to write a String value to [SharedPreferences].     *
         *
         * @param context a [Context] object.
         * @param key     is the key of the value
         * @param value   is the string value to be set in shared preference
         * @return true if the new value was successfully written to persistent storage.
         */
        fun setStringPreference(context: Context?, key: String, value: String?): Boolean {
            if (context != null) {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences != null && !TextUtils.isEmpty(key)) {
                    val editor = preferences.edit()
                    editor.putString(key, value)
                    return editor.commit()
                }
            }
            return false
        }

        /**
         * Helper method to retrieve a float value from [SharedPreferences].
         *
         * @param context      a [Context] object.
         * @param key          is the key of the value
         * @param defaultValue A default to return if the value could not be read.
         * @return The value from shared preferences, or the provided default.
         */
        fun getFloatPreference(context: Context?, key: String, defaultValue: Float): Float {
            var value = defaultValue
            if (context != null) {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences != null) {
                    value = preferences.getFloat(key, defaultValue)
                }
            }
            return value
        }

        /**
         * Helper method to write a float value to [SharedPreferences].
         *
         * @param context a [Context] object.
         * @param key     is the key of the value
         * @param value   is the float value to be set in shared preference
         * @return true if the new value was successfully written to persistent storage.
         */
        fun setFloatPreference(context: Context?, key: String, value: Float): Boolean {
            if (context != null) {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences != null) {
                    val editor = preferences.edit()
                    editor.putFloat(key, value)
                    return editor.commit()
                }
            }
            return false
        }

        /**
         * Helper method to retrieve a long value from [SharedPreferences].
         *
         * @param context      a [Context] object.
         * @param key          is the key of the value
         * @param defaultValue A default to return if the value could not be read.
         * @return The value from shared preferences, or the provided default.
         */
        fun getLongPreference(context: Context?, key: String, defaultValue: Long): Long {
            var value = defaultValue
            if (context != null) {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences != null) {
                    value = preferences.getLong(key, defaultValue)
                }
            }
            return value
        }

        /**
         * Helper method to write a long value to [SharedPreferences].
         *
         * @param context a [Context] object.
         * @param key     is the key of the value
         * @param value   is the long value to be set in shared preference
         * @return true if the new value was successfully written to persistent storage.
         */
        fun setLongPreference(context: Context?, key: String, value: Long): Boolean {
            if (context != null) {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences != null) {
                    val editor = preferences.edit()
                    editor.putLong(key, value)
                    return editor.commit()
                }
            }
            return false
        }

        /**
         * Helper method to retrieve an integer value from [SharedPreferences].
         *
         * @param context      a [Context] object.
         * @param key          is the key of the value
         * @param defaultValue A default to return if the value could not be read.
         * @return The value from shared preferences, or the provided default.
         */
        fun getIntegerPreference(context: Context?, key: String, defaultValue: Int): Int {
            var value = defaultValue
            if (context != null) {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences != null) {
                    value = preferences.getInt(key, defaultValue)
                }
            }
            return value
        }

        /**
         * Helper method to write an integer value to [SharedPreferences].
         *
         * @param context a [Context] object.
         * @param key     is the key of the value
         * @param value   is the int value to be set in shared preference
         * @return true if the new value was successfully written to persistent storage.
         */
        fun setIntegerPreference(context: Context?, key: String, value: Int): Boolean {
            if (context != null) {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences != null) {
                    val editor = preferences.edit()
                    editor.putInt(key, value)
                    return editor.commit()
                }
            }
            return false
        }

        /**
         * Helper method to retrieve a boolean value from [SharedPreferences].
         *
         * @param context      a [Context] object.
         * @param key          is the key of the value
         * @param defaultValue A default to return if the value could not be read.
         * @return The value from shared preferences, or the provided default.
         */
        fun getBooleanPreference(context: Context?, key: String, defaultValue: Boolean): Boolean {
            var value = defaultValue
            if (context != null) {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences != null) {
                    value = preferences.getBoolean(key, defaultValue)
                }
            }
            return value
        }

        /**
         * Helper method to write a boolean value to [SharedPreferences].
         *
         * @param context a [Context] object.
         * @param key     is the key of the value
         * @param value   is the boolean value to be set in shared preference
         * @return true if the new value was successfully written to persistent storage.
         */
        fun setBooleanPreference(context: Context?, key: String, value: Boolean): Boolean {
            if (context != null) {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences != null) {
                    val editor = preferences.edit()
                    editor.putBoolean(key, value)
                    return editor.commit()
                }
            }
            return false
        }
    }
}
