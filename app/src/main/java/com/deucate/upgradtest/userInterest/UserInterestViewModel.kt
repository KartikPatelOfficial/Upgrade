package com.deucate.upgradtest.userInterest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInterestViewModel : ViewModel() {

    private val selectedTag = MutableLiveData<ArrayList<String>>()

    init {
        selectedTag.value = ArrayList()
    }

}