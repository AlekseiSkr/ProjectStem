package com.example.projectstem.model.word

import com.example.projectstem.model.Word
import com.example.projectstem.model.WordDao


class WordRepository(private val wordDao: WordDao) {

    suspend fun getAllWordsInGroup(group_language_id : Int)
    {
        val getAllWordsInGroup : List<Word> = wordDao.getAllWordsInLanguageGroup(group_language_id)
    }

    suspend fun insertWordIntoGroup(word: Word)
    {
        wordDao.insertWordIntoGroup(word)
    }

}