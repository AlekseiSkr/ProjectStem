package com.example.projectstem.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT original FROM words WHERE group_language_id=:group_language_id")
    fun getOriginalByLanguageGroupId(group_language_id : Int) : List<String>

    @Query("SELECT translation FROM words WHERE group_language_id=:group_language_id")
    fun getTranslationByLanguageGroupId(group_language_id : Int) : List<String>

    @Query("INSERT INTO words VALUES (:id, :original, :translation, :knowledge)")
    fun insertLanguageGroup(id: Int, original: String, translation: String, knowledge: Int)
}