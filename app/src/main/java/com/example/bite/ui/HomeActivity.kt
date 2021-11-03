package com.example.bite.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.example.bite.api.RestAPI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //waiting on ssl certificates
        //var startUpData = getStartupData()
//        CoroutineScope(IO).launch {
//            val api = RestAPI()
//
//            val callResponse = api.getRestaurants()
//            val response = callResponse.execute()
//
//            if (response.isSuccessful) {
//                val restaurants = response.body()
//                Log.d("response", restaurants.toString())
//
//            } else {
//                Log.e("Api call error", response.message())
//            }
//        }

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BiteApp()
        }
    }
}
