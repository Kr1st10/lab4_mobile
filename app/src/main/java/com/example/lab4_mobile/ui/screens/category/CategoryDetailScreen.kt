package com.example.lab4_mobile.ui.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lab4_mobile.ui.screens.home.PlaceItem
import com.example.lab4_mobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(
    viewModel: CategoryDetailViewModel,
    categoryName: String,
    onPlaceClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(categoryName) {
        viewModel.loadCategory(categoryName)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(placeCategoryTitle(uiState.categoryName))) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
            ) {
                items(uiState.places) { place ->
                    PlaceItem(
                        place = place,
                        onClick = { onPlaceClick(place.id) }
                    )
                }
            }
        }
    }
}

private fun placeCategoryTitle(categoryName: String): Int {
    return when (categoryName) {
        "COFFEE_SHOPS" -> R.string.category_coffee_shops
        "RESTAURANTS" -> R.string.category_restaurants
        "PARKS" -> R.string.category_parks
        "MUSEUMS" -> R.string.category_museums
        "LANDMARKS" -> R.string.category_landmarks
        else -> R.string.app_name
    }
}