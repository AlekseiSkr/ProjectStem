package com.example.projectstem.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GroupDao {
    @Query("SELECT * FROM group_language")
    fun getAll(): LiveData<List<Group>>

    @Query("SELECT * FROM group_language WHERE group_id IN (:groupIds)")
    suspend fun loadAllByIds(groupIds: IntArray): List<Group>

    @Query("SELECT group_id FROM group_language WHERE language1 LIKE :language1 AND language2 LIKE :language2 LIMIT 1")
    suspend fun findByLanguageGroup(language1: String, language2: String) : List<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLanguageGroup(group: Group)
}