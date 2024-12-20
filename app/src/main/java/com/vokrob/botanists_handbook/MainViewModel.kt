package com.vokrob.botanists_handbook

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vokrob.botanists_handbook.db.MainDb
import com.vokrob.botanists_handbook.utils.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainDb: MainDb) : ViewModel() {
    val mainList = mutableStateOf(emptyList<ListItem>())

    fun getAllItemsByCategory(cat: String) = viewModelScope.launch {
        mainList.value = mainDb.dao.getAllItemsByCategory(cat)
    }

    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.insertItem(item)
    }

    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.deleteItem(item)
    }
}





























