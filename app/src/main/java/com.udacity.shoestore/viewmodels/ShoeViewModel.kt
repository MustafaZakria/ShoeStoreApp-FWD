package com.udacity.shoestore.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    val listOfShoes : LiveData<MutableList<Shoe>>
        get() = _listOfShoes

    private val _listOfShoes = MutableLiveData<MutableList<Shoe>>()

    init {
        _listOfShoes.value = mutableListOf()
    }

    fun addShoe(shoe: Shoe) {
        _listOfShoes.value?.apply {
            add(shoe)
            _listOfShoes.postValue(this)
        }
    }

}