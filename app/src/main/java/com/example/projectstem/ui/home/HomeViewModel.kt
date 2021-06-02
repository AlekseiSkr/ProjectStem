package com.example.projectstem.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to Stem \n" +
                "Here's some tasks for you to do:"
    }
    val text: LiveData<String> = _text
}