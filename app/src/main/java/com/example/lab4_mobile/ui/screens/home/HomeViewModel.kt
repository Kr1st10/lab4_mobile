package com.example.lab4_mobile.ui.screens.home

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

data class HomeUiState(
    val allPlaces: List<Place> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCategory: PlaceCategory? = null
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadPlaces()
    }

    private fun loadPlaces() {
        viewModelScope.launch {
            SeattlePlacesRepository.getAllPlacesStream().collect { places ->
                _uiState.update { currentState ->
                    currentState.copy(
                        allPlaces = places,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun selectCategory(category: PlaceCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun clearSelectedCategory() {
        _uiState.update { it.copy(selectedCategory = null) }
    }
}