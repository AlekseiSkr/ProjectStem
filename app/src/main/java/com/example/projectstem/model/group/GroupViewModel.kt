package com.example.projectstem.model.group

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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

     suspend fun getGroupId(language1: String, language2: String) :  Int {
        return  repository.getGroupId(language1, language2)
    }

    fun isRowIsExist(language1: String, language2: String){
        viewModelScope.launch(Dispatchers.IO) {
           repository.isRowIsExist(language1, language2)
        }
    }
}
