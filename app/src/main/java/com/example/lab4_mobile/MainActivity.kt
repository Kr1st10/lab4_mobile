package com.example.lab4_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.lab4_mobile.ui.screens.home.HomeViewModel
import com.example.lab4_mobile.ui.theme.Lab4_mobileTheme
import com.example.lab4_mobile.ui.theme.ThemeViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val usePermanentDrawer = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact

            val homeViewModel: HomeViewModel = viewModel()
            val themeViewModel: ThemeViewModel = viewModel(
                factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            )
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsStateWithLifecycle()

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val navController = rememberNavController()

            Lab4_mobileTheme(darkTheme = isDarkTheme) {
                MainScreen(
                    homeViewModel = homeViewModel,
                    themeViewModel = themeViewModel,
                    navController = navController,
                    drawerState = drawerState,
                    scope = scope,
                    usePermanentDrawer = usePermanentDrawer
                )
            }
        }
    }
}