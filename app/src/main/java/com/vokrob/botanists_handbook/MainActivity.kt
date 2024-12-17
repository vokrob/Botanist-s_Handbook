package com.vokrob.botanists_handbook

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.vokrob.botanists_handbook.ui.theme.Botanists_HandbookTheme
import com.vokrob.botanists_handbook.ui_components.DrawerMenu
import com.vokrob.botanists_handbook.ui_components.MainTopBar
import com.vokrob.botanists_handbook.utils.DrawerEvents
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        setContent {
            val scaffoldState = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()
            val topBarTitle = remember { mutableStateOf("Грибы") }

            Botanists_HandbookTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        MainTopBar(
                            title = topBarTitle.value,
                            scaffoldState
                        )
                    },
                    drawerContent = {
                        DrawerMenu() { event ->
                            when (event) {
                                is DrawerEvents.OnItemClick -> {
                                    topBarTitle.value = event.title
                                }
                            }
                            coroutineScope.launch { scaffoldState.drawerState.close() }
                        }
                    }
                ) { }
            }
        }
    }
}
















