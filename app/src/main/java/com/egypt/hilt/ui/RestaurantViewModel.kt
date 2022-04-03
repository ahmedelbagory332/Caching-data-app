package com.egypt.hilt.ui


import androidx.lifecycle.*
import com.egypt.hilt.data.Restaurant
import com.egypt.hilt.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope


@HiltViewModel
class RestaurantViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository)
    : ViewModel() {



    val isLoading: LiveData<Boolean> = restaurantRepository.isLoading


    val restaurants: LiveData<List<Restaurant>> = restaurantRepository.restaurants



   suspend fun getRestaurant(){

        restaurantRepository.getRestaurant()

    }



}