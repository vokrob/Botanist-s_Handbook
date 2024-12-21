package com.vokrob.botanists_handbook.ui_components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.vokrob.botanists_handbook.MainViewModel
import com.vokrob.botanists_handbook.utils.DrawerEvents
import com.vokrob.botanists_handbook.utils.ListItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onClick: (ListItem) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val mainList = mainViewModel.mainList
    val topBarTitle = remember { mutableStateOf(mainViewModel.getCurrentCategory()) }

    LaunchedEffect(Unit) {
        if (topBarTitle.value == "Избранное") mainViewModel.getFavorites()
        else mainViewModel.getAllItemsByCategory(topBarTitle.value)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(
                title = topBarTitle.value,
                scaffoldState
            ) {
                topBarTitle.value = "Избранное"
                mainViewModel.getFavorites()
            }
        },
        drawerContent = {
            DrawerMenu { event ->
                when (event) {
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle.value = event.title
                        mainViewModel.getAllItemsByCategory(event.title)
                    }
                }
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(mainList.value) { item ->
                MainListItem(item = item) { listItem ->
                    onClick(listItem)
                }
            }
        }
    }
}






























