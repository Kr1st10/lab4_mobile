package com.example.lab4_mobile.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.lab4_mobile.R
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.LocationOn

data class Place(
    val id: Int,
    @StringRes val nameResId: Int,
    @StringRes val descriptionResId: Int,
    @DrawableRes val imageResId: Int,
    val category: PlaceCategory
)

enum class PlaceCategory {
    COFFEE_SHOPS,
    RESTAURANTS,
    PARKS,
    MUSEUMS,
    LANDMARKS;

    val titleResId: Int
        get() = when (this) {
            COFFEE_SHOPS -> R.string.category_coffee_shops
            RESTAURANTS -> R.string.category_restaurants
            PARKS -> R.string.category_parks
            MUSEUMS -> R.string.category_museums
            LANDMARKS -> R.string.category_landmarks
        }

    val icon: androidx.compose.ui.graphics.vector.ImageVector
        get() = when (this) {
            COFFEE_SHOPS -> androidx.compose.material.icons.Icons.Default.FreeBreakfast
            RESTAURANTS -> androidx.compose.material.icons.Icons.Default.Restaurant
            PARKS -> androidx.compose.material.icons.Icons.Default.Park
            MUSEUMS -> androidx.compose.material.icons.Icons.Default.Museum
            LANDMARKS -> androidx.compose.material.icons.Icons.Default.LocationOn
        }
}