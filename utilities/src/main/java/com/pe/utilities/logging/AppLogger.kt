package com.pe.utilities.logging

import android.util.Log
import com.pe.utilities.BuildConfig

object AppLogger {
    private const val TAG = "AppLogger"

    fun v (tag: String = TAG, message: String) {
        if (BuildConfig.DEBUG){
            Log.v(tag, message)
        }
    }

    fun e (tag: String = TAG, message: String) {
        if (BuildConfig.DEBUG){
            Log.e(tag, message)
        }
    }
}