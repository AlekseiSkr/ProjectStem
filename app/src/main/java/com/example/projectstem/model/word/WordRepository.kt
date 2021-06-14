package com.example.projectstem.model.word

import androidx.lifecycle.LiveData
import com.example.projectstem.model.Word
import com.example.projectstem.model.WordDao


class WordRepository(private val wordDao: WordDao) {

    fun getAllWordsInGroup(group_language_id: Int): LiveData<List<Word>> {
        return wordDao.getAllWordsInLanguageGroup(group_language_id)
    }

    suspend fun insertWordIntoGroup(word: Word)
    {
        wordDao.insertWordIntoGroup(word)
    }

}