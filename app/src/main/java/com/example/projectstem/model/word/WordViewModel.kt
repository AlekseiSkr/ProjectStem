package com.example.projectstem.model.word

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectstem.model.AppDatabase
import com.example.projectstem.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository

    init {
        val wordDao = AppDatabase.getDatabase(application).wordDao()
        repository = WordRepository(wordDao)
    }

    fun getWordsFromGroup(id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllWordsInGroup(id)
        }
    }

    fun addWordsToGroup(word : Word){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWordIntoGroup(word)
        }
    }
}
