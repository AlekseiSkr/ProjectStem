package com.example.projectstem.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface GroupDao {
    @Query("SELECT * FROM group_language")
    fun getAll(): List<Group>

    @Query("SELECT * FROM group_language WHERE group_id IN (:groupIds)")
    fun loadAllByIds(groupIds: IntArray): List<Group>

    @Query("SELECT group_id FROM group_language WHERE language1 LIKE :language1 AND language2 LIKE :language2 LIMIT 1")
    fun findByLanguageGroup(language1: String, language2: String) : List<Int>

    @Query("INSERT INTO group_language VALUES (null, :l1, :l2)")
    fun insertLanguageGroup(l1: String, l2: String)
}