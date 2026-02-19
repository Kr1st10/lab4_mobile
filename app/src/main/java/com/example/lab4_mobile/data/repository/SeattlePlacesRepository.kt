package com.example.lab4_mobile.data.repository

import com.example.lab4_mobile.R
import com.example.lab4_mobile.data.model.Place
import com.example.lab4_mobile.data.model.PlaceCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object SeattlePlacesRepository {

    private val allPlaces = listOf(
        // Coffee Shops (5)
        Place(1, R.string.place_starbucks_reserve_name, R.string.place_starbucks_reserve_desc, R.drawable.starbucks, PlaceCategory.COFFEE_SHOPS),
        Place(2, R.string.place_victrola_name, R.string.place_victrola_desc, R.drawable.victrola, PlaceCategory.COFFEE_SHOPS),
        Place(3, R.string.place_elm_name, R.string.place_elm_desc, R.drawable.elm, PlaceCategory.COFFEE_SHOPS),
        Place(4, R.string.place_fulcrum_name, R.string.place_fulcrum_desc, R.drawable.fulcrum, PlaceCategory.COFFEE_SHOPS),
        Place(5, R.string.place_anchorhead_name, R.string.place_anchorhead_desc, R.drawable.anchorhead, PlaceCategory.COFFEE_SHOPS),

        // Restaurants (5)
        Place(6, R.string.place_canlis_name, R.string.place_canlis_desc, R.drawable.canlis, PlaceCategory.RESTAURANTS),
        Place(7, R.string.place_il_bistro_name, R.string.place_il_bistro_desc, R.drawable.il_bistro, PlaceCategory.RESTAURANTS),
        Place(8, R.string.place_taylor_shellfish_name, R.string.place_taylor_shellfish_desc, R.drawable.shelfish, PlaceCategory.RESTAURANTS),
        Place(9, R.string.place_dick_s_name, R.string.place_dick_s_desc, R.drawable.dicks, PlaceCategory.RESTAURANTS),
        Place(10, R.string.place_paseo_name, R.string.place_paseo_desc, R.drawable.paseo, PlaceCategory.RESTAURANTS),

        // Parks (5)
        Place(11, R.string.place_discovery_park_name, R.string.place_discovery_park_desc, R.drawable.discovery, PlaceCategory.PARKS),
        Place(12, R.string.place_volunteer_park_name, R.string.place_volunteer_park_desc, R.drawable.volunteer, PlaceCategory.PARKS),
        Place(13, R.string.place_gas_works_name, R.string.place_gas_works_desc, R.drawable.gas_works, PlaceCategory.PARKS),
        Place(14, R.string.place_kerry_park_name, R.string.place_kerry_park_desc, R.drawable.kerry, PlaceCategory.PARKS),
        Place(15, R.string.place_golden_gardens_name, R.string.place_golden_gardens_desc, R.drawable.golden_gardens, PlaceCategory.PARKS),

        // Museums (5)
        Place(16, R.string.place_mopop_name, R.string.place_mopop_desc, R.drawable.mopop, PlaceCategory.MUSEUMS),
        Place(17, R.string.place_sam_name, R.string.place_sam_desc, R.drawable.art_museum, PlaceCategory.MUSEUMS),
        Place(18, R.string.place_wing_luke_name, R.string.place_wing_luke_desc, R.drawable.wing_luke, PlaceCategory.MUSEUMS),
        Place(19, R.string.place_chi_mei_name, R.string.place_chi_mei_desc, R.drawable.chihuli, PlaceCategory.MUSEUMS),
        Place(20, R.string.place_nordic_name, R.string.place_nordic_desc, R.drawable.nordic, PlaceCategory.MUSEUMS),

        // Landmarks (5)
        Place(21, R.string.place_space_needle_name, R.string.place_space_needle_desc, R.drawable.space, PlaceCategory.LANDMARKS),
        Place(22, R.string.place_pike_place_name, R.string.place_pike_place_desc, R.drawable.pike_place, PlaceCategory.LANDMARKS),
        Place(23, R.string.place_great_wheel_name, R.string.place_great_wheel_desc, R.drawable.great_wheel, PlaceCategory.LANDMARKS),
        Place(24, R.string.place_waterfront_name, R.string.place_waterfront_desc, R.drawable.water_front, PlaceCategory.LANDMARKS),
        Place(25, R.string.place_uu_name, R.string.place_uu_desc, R.drawable.university, PlaceCategory.LANDMARKS),
    )

    fun getAllPlacesStream(): Flow<List<Place>> = flowOf(allPlaces)

    fun getPlacesByCategory(category: PlaceCategory): List<Place> = allPlaces.filter { it.category == category }

    fun getPlaceById(id: Int): Place? = allPlaces.find { it.id == id }
}