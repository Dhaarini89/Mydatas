package com.android.example.exercise1.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.exercise1.repository.model

class mainviewmodel: ViewModel() {
    private val modelvalue= model(samplevalue = "sample value")

     val textLiveData =MutableLiveData<String>()

    fun updatetext( text: String)
    {
        textLiveData.postValue(modelvalue.samplevalue+text)
    }

    fun updatetestsecond()
    {
        modelvalue.samplevalue="changed"
        textLiveData.postValue(modelvalue.samplevalue)
    }


}