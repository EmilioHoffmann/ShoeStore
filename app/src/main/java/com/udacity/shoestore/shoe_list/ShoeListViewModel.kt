package com.udacity.shoestore.shoe_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.core.utils.SingleLiveEvent
import com.udacity.shoestore.shoe_list.model.Shoe

class ShoeListViewModel : ViewModel() {
    var shoe = Shoe()

    private val _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    private val _shoeIsEmptyWarning = SingleLiveEvent<Boolean>()
    val shoeIsEmptyWarning: LiveData<Boolean>
        get() = _shoeIsEmptyWarning

    fun validateNewShoe() {
        if (shoe.isEmpty()) {
            _shoeIsEmptyWarning.postValue(true)
        } else {
            val list = _shoeList.value ?: arrayListOf()
            list.add(shoe)
            _shoeList.postValue(list)
            _shoeIsEmptyWarning.postValue(false)
        }
    }
}
