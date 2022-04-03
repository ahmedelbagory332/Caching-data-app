package com.egypt.hilt.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.room.withTransaction
import com.egypt.hilt.api.RestaurantApi
 import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import javax.inject.Inject


class RestaurantRepository @Inject constructor(private val api: RestaurantApi, private val db: RestaurantDatabase, private val ctx: Application)   {

    private val restaurantDao = db.restaurantDao()



    private val _isLoading = MutableStateFlow(true)

    val isLoading: LiveData<Boolean>
    get() = _isLoading.asLiveData()


    private val restaurantsLiveData = MutableStateFlow<List<Restaurant>>(listOf())

    val restaurants: LiveData<List<Restaurant>>
    get() = restaurantsLiveData.asLiveData()




  suspend  fun getRestaurant() {

             _isLoading.emit(true)
            try {

                db.withTransaction {
                    restaurantDao.deleteAllRestaurants()
                    restaurantDao.insertRestaurants(api.getRestaurants())
                }

                restaurantDao.getAllRestaurants().collect {
                    restaurantsLiveData.emit(it)
                    _isLoading.emit(false)
                }



            } catch (e: Exception) {

                restaurantDao.getAllRestaurants().collect {
                    restaurantsLiveData.emit(it)
                    _isLoading.emit(false)
                }
            }


    }
}