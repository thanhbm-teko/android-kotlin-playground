package org.playground.android.data

import androidx.lifecycle.liveData
import kotlinx.coroutines.delay
import timber.log.Timber

object DataManager {

    fun getStringDelayed(delayTime: Long) = liveData {
        delay(delayTime)
        Timber.d("$delayTime emitting string result")
        emit("Welcome after $delayTime")
    }
}