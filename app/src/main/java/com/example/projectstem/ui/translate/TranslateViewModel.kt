package com.example.projectstem.ui.translate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TranslateViewModel : ViewModel() {

    /**
     * This function write out the text on the page of the translate
     * MutableLiveData a class that extends the LiveData type class
     * @constructor value includes a general string
     * @return the text value
     */
    private val _text = MutableLiveData<String>().apply {
        value = "This is translate Fragment"
    }
    val text: LiveData<String> = _text
}