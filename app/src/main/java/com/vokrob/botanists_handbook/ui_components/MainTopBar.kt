package com.vokrob.botanists_handbook.ui_components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun MainTopBar(
    title: String,
    scaffoldState: ScaffoldState,
    onFavClick: () -> Unit
) {
    val coroutine = rememberCoroutineScope()

    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(
                onClick = { coroutine.launch { scaffoldState.drawerState.open() } }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onFavClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite"
                )
            }
        }
    )
}

























