package com.udacity.shoestore.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.core.utils.SingleLiveEvent

class SplashViewModel : ViewModel() {

    private val _endTimerEvent = SingleLiveEvent<Boolean>()
    val endTimerEvent: LiveData<Boolean>
        get() = _endTimerEvent

    init {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                _endTimerEvent.postValue(true)
            },
            SPLASH_TIME
        )
    }

    companion object {
        private const val SPLASH_TIME: Long = 3500
    }
}
