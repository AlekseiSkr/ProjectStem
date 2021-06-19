package com.example.projectstem.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    /**
     * This function write out the text of the home page
     * MutableLiveData a class that extends the LiveData type class
     * @constructor value includes the general welcome text
     * @return a welcome text and a list with logos
     */
    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to Stem \n" +
                "Here's some tasks for you to do:"
    }
    val text: LiveData<String> = _text
}