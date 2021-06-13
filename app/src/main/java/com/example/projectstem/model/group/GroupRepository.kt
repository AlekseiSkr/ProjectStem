package com.example.projectstem.model.group

import androidx.lifecycle.LiveData
import com.example.projectstem.model.Group
import com.example.projectstem.model.GroupDao

class GroupRepository(private val groupDao: GroupDao) {

    val getAllLanguageGroups: LiveData<List<Group>> = groupDao.getAll()

    suspend fun insertLanguageGroup(group: Group) {
        groupDao.insertLanguageGroup(group)
    }

    suspend fun getGroupId(language1: String, language2: String): List<Int> {
        return groupDao.findByLanguageGroup(language1, language2)
    }

    fun isRowIsExist(language1: String, language2: String) : Boolean{
        return groupDao.isRowIsExist(language1, language2)
    }
}