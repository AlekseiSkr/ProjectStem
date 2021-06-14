package com.example.projectstem.model.word

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projectstem.model.AppDatabase
import com.example.projectstem.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var getAllWords: LiveData<List<Word>>
    private val repository: WordRepository

    init {
        val wordDao = AppDatabase.getDatabase(application).wordDao()
        repository = WordRepository(wordDao)
    }

    fun getWordsFromGroup(id: Int) : LiveData<List<Word>> {
        return repository.getAllWordsInGroup(id)
    }

    fun addWordsToGroup(word : Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWordIntoGroup(word)
        }
    }

    fun getGameWords(id: Int) : List<Word>{
        return repository.getGameWords(id)
    }
}
