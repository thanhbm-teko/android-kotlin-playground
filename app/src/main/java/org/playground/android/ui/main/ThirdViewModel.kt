package org.playground.android.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.playground.android.data.DataManager
import org.playground.android.ui.base.BaseViewModel
import timber.log.Timber

class ThirdViewModel : BaseViewModel<NavigationView>() {
    var title = MutableLiveData<String>("Fragment 3")
    val welcomeText = MutableLiveData<String>("Welcome")

    fun onButton1Clicked() {
        welcomeText.value = "Button 1 clicked"
    }

    fun onButton2Clicked() {
        viewModelScope.launch {
            val titleLd = DataManager.getStringDelayed(1000)
            titleLd.observeForever {
                Timber.d("got data")
                welcomeText.value = it
            }
            Timber.d("returning")
        }
    }

    fun next() {
        getView()?.onNext()
    }

    fun back() {
        getView()?.onBack()
    }
}
