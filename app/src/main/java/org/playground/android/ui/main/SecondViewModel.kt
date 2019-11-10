package org.playground.android.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.playground.android.data.DataManager
import org.playground.android.ui.base.BaseViewModel
import timber.log.Timber

class SecondViewModel : BaseViewModel<NavigationView>() {
    var title = MutableLiveData<String>("Fragment 2")
    val welcomeText = MutableLiveData<String>("Welcome")
    var welcomeText2: LiveData<String> = MutableLiveData<String>("Welcome 2")
    var welcomeTextDelay = Transformations.map(DataManager.getStringDelayed(5000)) {
        Timber.d("got data")
        it
    }

    fun onButton1Clicked() {
        welcomeText.value = "Button 1 clicked"
    }

    // even when the fragment is destroyed, the callback still trigger
    fun onButton2Clicked() {
        viewModelScope.launch {
            val titleLd = DataManager.getStringDelayed(10000)
            titleLd.observeForever {
                Timber.d("got data")
                welcomeText.value = "onButton2Clicked: $it"
            }
            Timber.d("returning")
        }
    }

    // object welcomeText2 changed, nothing listen for the new object so getStringDelayed is not triggered
    fun onButton3Clicked() {
        viewModelScope.launch {
            welcomeText2 = DataManager.getStringDelayed(10000).map {
                Timber.d("got data")
                it
            }
            Timber.d("returning")
        }
    }

    // use BaseViewModel.performTask to properly manage LiveData and Observer
    fun onButton4Clicked() {
        viewModelScope.launch {
            performTask(DataManager.getStringDelayed(10000)) {
                Timber.d("got data")
                welcomeText.value = "onButton4Clicked: $it"
            }
            Timber.d("returning")
        }
    }

    fun getStringDelayedLiveData() = DataManager.getStringDelayed(7000)

    fun next() {
        getView()?.onNext()
    }

    fun back() {
        getView()?.onBack()
    }
}
