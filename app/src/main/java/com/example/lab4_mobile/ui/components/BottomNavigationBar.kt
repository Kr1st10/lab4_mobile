package com.example.lab4_mobile.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.lab4_mobile.data.model.PlaceCategory

@Composable
fun AppBottomNavigationBar(
    selectedCategory: PlaceCategory?,
    onCategorySelected: (PlaceCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val bottomCategories = listOf(
        PlaceCategory.COFFEE_SHOPS,
        PlaceCategory.RESTAURANTS,
        PlaceCategory.PARKS
    )

    NavigationBar(modifier = modifier) {
        bottomCategories.forEach { category ->
            NavigationBarItem(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                icon = {
                    Icon(
                        imageVector = when (category) {
                            PlaceCategory.COFFEE_SHOPS -> Icons.Default.Coffee
                            PlaceCategory.RESTAURANTS -> Icons.Default.Restaurant
                            PlaceCategory.PARKS -> Icons.Default.Park
                            else -> Icons.Default.Coffee
                        },
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(category.titleResId)) }
            )
        }
    }
}