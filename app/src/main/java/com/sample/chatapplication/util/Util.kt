package com.sample.chatapplication.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.sample.chatapplication.R
import java.util.*


class Util {
    companion object {


        fun openCustomTab(activity: Activity, url: String, isNormalUrlOnly: Boolean) {
            openChrome(activity, url, isNormalUrlOnly)
        }

        fun openChrome(activity: Activity, url: String, isNormalUrlOnly: Boolean) {
            var finalUrl = ""
            if (isNormalUrlOnly) {
                finalUrl = url
            } else {
//                val label = SharedPrefsUtils.getPref(Constants.LABEL_AD)
//                finalUrl = "$url&splash=false&label=$label"
            }


            val uri = Uri.parse(finalUrl)
            val customIntent = CustomTabsIntent.Builder()
//            customIntent.setToolbarColor(ContextCompat.getColor(AppController.getInstance(), R.color.colorPrimary))
            customIntent.setShowTitle(true)

            val customTabsIntent = customIntent.build()

            customIntent.setStartAnimations(activity, R.anim.slide_in, R.anim.slide_in)
            customIntent.setExitAnimations(activity, R.anim.slide_out, R.anim.slide_out)
            // package name is the default package
            val packageName = "com.android.chrome"
            try {
                if (packageName != null) {

                    // we are checking if the package name is not null
                    // if package name is not null then we are calling
                    // that custom chrome tab with intent by passing its
                    // package name.
                    customTabsIntent.intent.setPackage(packageName)

                    // in that custom tab intent we are passing
                    // our url which we have to browse.
                    customTabsIntent.launchUrl(activity, uri)
                } else {
                    // if the custom tabs fails to load then we are simply
                    // redirecting our user to users device default browser.
                    activity.startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
            } catch (e: Exception) {
            }
        }



    }

}