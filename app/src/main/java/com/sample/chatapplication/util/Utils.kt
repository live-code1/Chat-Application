package com.sample.chatapplication.util

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment


object Utils {


    /**
     * This method is used to show progress bar on the screen.
     *
     * @param progressDialog of the application or the activity
     * @param title          of the progress dialog
     * @param message        of the progress dialog
     */
    fun showProgressDialog(progressDialog: ProgressDialog?, title: String, message: String) {

        if (progressDialog != null) {
            progressDialog.setTitle(title)
            progressDialog.setMessage(message)
            progressDialog.show()
        }
    }

    /**
     * This method is used to hide the progress dialog
     *
     * @param progressDialog of the application or activity
     */
    fun hideProgressDialog(progressDialog: ProgressDialog?) {

        if (progressDialog != null && progressDialog.isShowing) {
            progressDialog.hide()
            progressDialog.dismiss()
        }
    }

    

    fun Activity.hideKeyboard(context: Context) {
        hideKeyboard(currentFocus ?: View(this), context)
    }

    fun Context.hideKeyboard(view: View, context: Context) {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}


