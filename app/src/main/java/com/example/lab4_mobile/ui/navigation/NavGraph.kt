package com.example.lab4_mobile.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lab4_mobile.ui.screens.category.CategoryDetailScreen
import com.example.lab4_mobile.ui.screens.category.CategoryDetailViewModel
import com.example.lab4_mobile.ui.screens.details.PlaceDetailScreen
import com.example.lab4_mobile.ui.screens.home.HomeScreen
import com.example.lab4_mobile.ui.screens.home.HomeViewModel
import com.example.lab4_mobile.ui.screens.about.AboutScreen
import com.example.lab4_mobile.ui.screens.settings.SettingsScreen
import com.example.lab4_mobile.ui.theme.ThemeViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    themeViewModel: ThemeViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                viewModel = homeViewModel,
                onCategoryClick = { categoryName ->
                    navController.navigate(Screen.CategoryDetail.passCategoryName(categoryName))
                },
                onPlaceClick = { placeId ->
                    navController.navigate(Screen.PlaceDetail.passPlaceId(placeId))
                },
                onNavigateToAbout = {
                    navController.navigate(Screen.About.route)
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(route = Screen.CategoryDetail.route) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            val categoryDetailViewModel: CategoryDetailViewModel = viewModel(
                viewModelStoreOwner = backStackEntry
            )
            CategoryDetailScreen(
                viewModel = categoryDetailViewModel,
                categoryName = categoryName,
                onPlaceClick = { placeId ->
                    navController.navigate(Screen.PlaceDetail.passPlaceId(placeId))
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(route = Screen.PlaceDetail.route) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getString("placeId")?.toIntOrNull() ?: 0
            PlaceDetailScreen(
                placeId = placeId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(route = Screen.About.route) {
            AboutScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(route = Screen.Settings.route) {
            SettingsScreen(
                themeViewModel = themeViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
