package com.udacity.shoestore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    val listOfShoes = MutableLiveData<MutableList<Shoe>>()

    fun addShoe(shoe: Shoe) {
        listOfShoes.value?.apply {
            add(shoe)
            listOfShoes.postValue(this)
        }
    }

}