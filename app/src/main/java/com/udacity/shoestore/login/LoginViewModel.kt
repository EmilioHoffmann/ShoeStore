package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.shoestore.core.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var email: String = ""
    var password: String = ""

    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid: LiveData<Boolean>
        get() = _isEmailValid

    private val _isPasswordValid = MutableLiveData<Boolean>()
    val isPasswordValid: LiveData<Boolean>
        get() = _isPasswordValid

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _shouldNavigate = SingleLiveEvent<Boolean>()
    val shouldNavigate: LiveData<Boolean>
        get() = _shouldNavigate

    fun validateFields() {

        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            _isPasswordValid.postValue(password.length >= 8)
            _isEmailValid.postValue(email.contains("@"))
            delay(250)
            _isLoading.postValue(false)

            if (_isEmailValid.value == true && _isPasswordValid.value == true) {
                _shouldNavigate.postValue(true)
            }
        }
    }
}
