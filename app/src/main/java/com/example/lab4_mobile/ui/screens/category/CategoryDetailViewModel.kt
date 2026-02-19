package com.example.lab4_mobile.ui.screens.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab4_mobile.data.model.Place
import com.example.lab4_mobile.data.model.PlaceCategory
import com.example.lab4_mobile.data.repository.SeattlePlacesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CategoryDetailUiState(
    val categoryName: String = "",
    val places: List<Place> = emptyList(),
    val isLoading: Boolean = false
)

class CategoryDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryDetailUiState())
    val uiState: StateFlow<CategoryDetailUiState> = _uiState.asStateFlow()

    fun loadCategory(categoryName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val category = try {
                PlaceCategory.valueOf(categoryName)
            } catch (e: IllegalArgumentException) {
                null
            }
            val places = category?.let { SeattlePlacesRepository.getPlacesByCategory(it) } ?: emptyList()
            _uiState.update {
                it.copy(
                    categoryName = categoryName,
                    places = places,
                    isLoading = false
                )
            }
        }
    }
}

