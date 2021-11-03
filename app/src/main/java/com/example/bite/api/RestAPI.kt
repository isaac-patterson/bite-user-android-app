package com.example.bite.api

import UserBFFApi
import com.example.bite.model.Restaurant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestAPI {
    private val userBFFApi: UserBFFApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://public-stack-elb-705744411.us-east-1.elb.amazonaws.com/user/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        userBFFApi = retrofit.create(UserBFFApi::class.java)
    }

    fun getRestaurants(): Call<List<Restaurant>> {
        return userBFFApi.getRestaurants()
    }
}