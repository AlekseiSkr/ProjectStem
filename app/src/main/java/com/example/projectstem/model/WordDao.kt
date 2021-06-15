package com.example.projectstem.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {
    @Query("SELECT original FROM words WHERE group_language_id IN (:group_language_id)")
    fun getOriginalByLanguageGroupId(group_language_id: Int): LiveData<List<String>>

    @Query("SELECT translation FROM words WHERE group_language_id IN (:group_language_id)")
    fun getTranslationByLanguageGroupId(group_language_id: Int): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWordIntoGroup(word: Word)

    @Query("SELECT * FROM words WHERE group_language_id IN (:group_language_id)")
    fun getAllWordsInLanguageGroup(group_language_id: Int): LiveData<List<Word>>

    @Query("SELECT * FROM words WHERE group_language_id IN (:group_language_id)")
    fun getGameWordsInGroup(group_language_id: Int): List<Word>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(word: Word)

    @Query("DELETE FROM words WHERE word_id = :wordId")
    fun deleteWord(wordId: Int)

    @Query("SELECT knowledge FROM words WHERE translation IN (:word)")
    fun getKnowledgeFromTranslation(word: String) : Int

    @Query("UPDATE words SET knowledge = (SELECT knowledge FROM words WHERE translation IN (:word)) + 1 WHERE translation IN (:word)")
    fun updateKnowledgeFromWord(word: String)
}