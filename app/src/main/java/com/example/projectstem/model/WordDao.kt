package com.example.projectstem.model

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * This DAO of Word table interface is responsible for initializing and holding all queries which
 * are needed for the application to interact with the database
 */
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

    @Query("SELECT * FROM words WHERE group_language_id IN (:group_language_id) AND knowledge < 3")
    fun getGameWordsInGroup(group_language_id: Int): List<Word>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(word: Word)

    @Query("DELETE FROM words WHERE word_id = :wordId")
    fun deleteWord(wordId: Int)

    @Query("SELECT knowledge FROM words WHERE original IN (:word) AND  group_language_id IN (:group_language_id)")
    fun getKnowledgeFromWord(word: String, group_language_id: Int) : Int

    @Query("UPDATE words SET knowledge = (SELECT knowledge FROM words WHERE original IN (:word)) + 1 WHERE original IN (:word) AND  group_language_id IN (:group_language_id)")
    fun incrementKnowledgeFromWord(word: String, group_language_id: Int)
}