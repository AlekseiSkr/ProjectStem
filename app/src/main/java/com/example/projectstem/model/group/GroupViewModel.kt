package com.example.projectstem.model.group

import android.app.Application
import androidx.lifecycle.*
import com.example.projectstem.model.AppDatabase
import com.example.projectstem.model.Group
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupViewModel(application: Application) : AndroidViewModel(application) {

    val getAllLanguageGroups: LiveData<List<Group>>
    private val repository: GroupRepository

    init {
        val groupDao = AppDatabase.getDatabase(application).groupDao()
        repository = GroupRepository(groupDao)
        getAllLanguageGroups = repository.getAllLanguageGroups
    }

    fun addLanguageGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertLanguageGroup(group)
        }
    }
}
