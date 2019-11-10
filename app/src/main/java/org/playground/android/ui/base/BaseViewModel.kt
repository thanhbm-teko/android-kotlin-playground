package org.playground.android.ui.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import java.lang.ref.WeakReference

abstract class BaseViewModel<VIEW> : ViewModel() {

    private var view: WeakReference<VIEW>? = null

    @VisibleForTesting
    internal var scope: CoroutineScope = viewModelScope

    private val dataWaiters: MutableMap<LiveData<Any>, Observer<Any>> = mutableMapOf()

    fun getView(): VIEW? = view?.get()

    fun setView(view: VIEW) {
        this.view = WeakReference(view)
    }

    fun <T: Any> performTask(source: LiveData<T>, block: (T?) -> Unit) {
        val observer = object: Observer<T> {
            override fun onChanged(t: T?) {
                source.removeObserver(this)
                dataWaiters.remove(source as LiveData<Any>)
                block(t)
            }
        }
        dataWaiters[source as LiveData<Any>] = observer as Observer<Any>
        source.observeForever(observer)
    }

    override fun onCleared() {
        dataWaiters.forEach {
            it.key.removeObserver(it.value)
        }
        super.onCleared()
    }
}
