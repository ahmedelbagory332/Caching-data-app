package com.egypt.hilt.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "restaurants")
data class Restaurant(
    @PrimaryKey val name: String,
    val address: String,
    val description: String,
    val id: Int,
    val logo: String,
    val phone_number: String,
    val review: String,
    val type: String,
    val uid: String
)

