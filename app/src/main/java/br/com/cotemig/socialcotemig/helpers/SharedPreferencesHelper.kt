package br.com.cotemig.socialcotemig.helpers

import android.content.Context

class SharedPreferencesHelper {

//    public static void saveString()

    companion object {

        fun saveString(context: Context, fileName: String, key: String, value: String) {
            var preferences =
                context.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            var editor = preferences.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun readString(context: Context, fileName: String, key: String, default: String): String? {
            var preferences =
                context.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            return preferences.getString(key, default)
        }

        fun readString(context: Context, fileName: String, key: String): String? {
            var preferences =
                context.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            return preferences.getString(key, "")
        }

        fun delete(context: Context, fileName: String, key: String) {
            var preferences =
                context.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            var editor = preferences.edit()
            editor.remove(key)
            editor.apply()
        }

    }

}