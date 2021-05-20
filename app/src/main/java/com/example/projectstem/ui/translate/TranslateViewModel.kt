package com.example.projectstem.ui.translate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TranslateViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is translate Fragment"
    }
    val text: LiveData<String> = _text
}