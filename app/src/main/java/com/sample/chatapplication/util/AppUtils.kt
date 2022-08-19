package com.sample.chatapplication.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

object AppUtils {
    var mBaseUrl = "https://f72c6914-251a-40da-acf1-bd53216de1e5.mock.pstmn.io/"


    fun isNetworkAvailable(context: Context?): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetwork = cm?.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }


    fun hideKeyboard(hostActivity: FragmentActivity?) {
        val inputMethodManager =
                hostActivity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            inputMethodManager.hideSoftInputFromWindow(hostActivity.currentFocus!!.windowToken, 0)
        } catch (ex: NullPointerException) {
            Log.v("HideKeyboard", ex.toString())
        }
    }

}