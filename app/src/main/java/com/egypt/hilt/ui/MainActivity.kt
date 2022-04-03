 package com.egypt.hilt.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.egypt.hilt.R
import com.egypt.hilt.adapter.RestaurantAdapter

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

 @AndroidEntryPoint
class MainActivity : AppCompatActivity() {

     private val viewModel: RestaurantViewModel by viewModels()

     @Inject
     lateinit var restaurantAdapter: RestaurantAdapter


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         val restaurantsList: RecyclerView = findViewById(R.id.recycler_view)
         val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

         restaurantsList.adapter = restaurantAdapter

         lifecycleScope.launchWhenStarted {
             viewModel.getRestaurant()

         }

         viewModel.restaurants.observe(this, Observer {
             restaurantAdapter.submitList(it)
         })

         viewModel.isLoading.observe(this, Observer {
             if (it) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
         })


     }
 }


