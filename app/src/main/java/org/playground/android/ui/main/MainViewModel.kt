package org.playground.android.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val welcomeText = MutableLiveData<String>("Welcome")
}
