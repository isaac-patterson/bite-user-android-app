package com.example.bite.model

import androidx.compose.runtime.Immutable
import java.util.*

@Immutable
data class Restaurant(
    val RestaurantId : Int,
    val Name : String,
    val Description : String,
    val CountryCode : String,
    val Address : String,
    //val CreatedTime : Date
)
