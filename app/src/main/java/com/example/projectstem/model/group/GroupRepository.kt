package com.example.projectstem.model.group

import androidx.lifecycle.LiveData
import com.example.projectstem.model.Group
import com.example.projectstem.model.GroupDao

class GroupRepository(private val groupDao: GroupDao) {

    val getAllLanguageGroups: LiveData<List<Group>> = groupDao.getAll()

    suspend fun insertLanguageGroup(group: Group)
    {
        groupDao.insertLanguageGroup(group)
    }
}