package com.example.lab4_mobile.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CategoryDetail : Screen("category/{categoryName}") {
        fun passCategoryName(categoryName: String): String = "category/$categoryName"
    }
    object PlaceDetail : Screen("place/{placeId}") {
        fun passPlaceId(placeId: Int): String = "place/$placeId"
    }
    object About : Screen("about")
    object Settings : Screen("settings")
}