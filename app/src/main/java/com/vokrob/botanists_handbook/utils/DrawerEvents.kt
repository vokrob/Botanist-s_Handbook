package com.vokrob.botanists_handbook.utils

sealed class DrawerEvents {
    data class OnItemClick(val title: String, val index: Int) : DrawerEvents()
}































