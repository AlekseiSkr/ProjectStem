package com.example.projectstem.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT original FROM words WHERE group_language_id IN (:group_language_id)")
    suspend fun getOriginalByLanguageGroupId(group_language_id : Int) : List<String>

    @Query("SELECT translation FROM words WHERE group_language_id IN (:group_language_id)")
    suspend fun getTranslationByLanguageGroupId(group_language_id : Int) : List<String>

    @Query("INSERT INTO words VALUES (:id, :original, :translation, :knowledge)")
    suspend fun insertWordIntoGroup(id: Int, original: String, translation: String, knowledge: Int)

    @Query("SELECT * FROM words WHERE group_language_id IN (:group_language_id)")
    suspend fun getAllWordsInLanguageGroup(group_language_id: Int) : List<Word>




}