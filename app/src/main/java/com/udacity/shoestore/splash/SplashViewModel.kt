package com.udacity.shoestore.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.shoestore.core.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _endTimerEvent = SingleLiveEvent<Boolean>()
    val endTimerEvent: LiveData<Boolean>
        get() = _endTimerEvent

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(SPLASH_TIME)
            _endTimerEvent.postValue(true)
        }
    }

    companion object {
        private const val SPLASH_TIME: Long = 3500
    }
}
