package com.example.lab4_mobile

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.lab4_mobile.ui.components.AppBottomNavigationBar
import com.example.lab4_mobile.ui.components.AdaptiveNavigationDrawer
import com.example.lab4_mobile.ui.navigation.AppNavGraph
import com.example.lab4_mobile.ui.navigation.Screen
import com.example.lab4_mobile.ui.screens.home.HomeViewModel
import com.example.lab4_mobile.ui.theme.ThemeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    homeViewModel: HomeViewModel,
    themeViewModel: ThemeViewModel,
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    usePermanentDrawer: Boolean
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    AdaptiveNavigationDrawer(
        usePermanentDrawer = usePermanentDrawer,
        drawerState = drawerState,
        onCategoryClick = { categoryName ->
            scope.launch { if (!usePermanentDrawer) drawerState.close() }
            navController.navigate(Screen.CategoryDetail.passCategoryName(categoryName))
        },
        onAboutClick = {
            scope.launch { if (!usePermanentDrawer) drawerState.close() }
            navController.navigate(Screen.About.route)
        },
        onSettingsClick = {
            scope.launch { if (!usePermanentDrawer) drawerState.close() }
            navController.navigate(Screen.Settings.route)
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    navigationIcon = {
                        if (!usePermanentDrawer) {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    }
                )
            },
            bottomBar = {
                AppBottomNavigationBar(
                    selectedCategory = uiState.selectedCategory,
                    onCategorySelected = { category ->
                        homeViewModel.selectCategory(category)
                        navController.navigate(Screen.CategoryDetail.passCategoryName(category.name)) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        ) { paddingValues ->
            AppNavGraph(
                navController = navController,
                homeViewModel = homeViewModel,
                themeViewModel = themeViewModel,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}