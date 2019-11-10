package org.playground.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.playground.android.ui.main.MainFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        Timber.plant(Timber.DebugTree())
    }

}
