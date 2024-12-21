package com.vokrob.botanists_handbook

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vokrob.botanists_handbook.db.MainDb
import com.vokrob.botanists_handbook.utils.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainDb: MainDb) : ViewModel() {
    val mainList = mutableStateOf<List<ListItem>>(emptyList())
    private var job: Job? = null
    private var currentCategory = mutableStateOf("Грибы")

    fun getAllItemsByCategory(cat: String) {
        currentCategory.value = cat
        job?.cancel()
        job = viewModelScope.launch {
            mainDb.dao.getAllItemsByCategory(cat).collect { list ->
                mainList.value = list
            }
        }
    }

    fun getFavorites() {
        currentCategory.value = "Избранное"
        job?.cancel()
        job = viewModelScope.launch {
            mainDb.dao.getFavorites().collect { list ->
                mainList.value = list
            }
        }
    }

    fun getCurrentCategory(): String = currentCategory.value

    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.insertItem(item)
    }
}





























