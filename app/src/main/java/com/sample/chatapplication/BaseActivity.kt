package com.sample.chatapplication

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        val window: Window = window
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

// clear FLAG_TRANSLUCENT_STATUS flag:

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

// finally change the color

// finally change the color
        window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
    }
}
