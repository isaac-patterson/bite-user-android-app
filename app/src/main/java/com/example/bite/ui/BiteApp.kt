package com.example.bite.ui

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.bite.api.RestAPI
import com.example.bite.model.StartUpData
import com.example.bite.ui.components.BiteScaffold
import com.example.bite.ui.home.HomeSections
import com.example.bite.ui.home.BiteBottomBar
import com.example.bite.ui.theme.BiteTheme
import com.google.accompanist.insets.ProvideWindowInsets

//called if the user is signed in
@ExperimentalAnimationApi
@Composable
fun BiteApp() {
    ProvideWindowInsets {
        BiteTheme {
            val tabs = remember { HomeSections.values() }
            val navController = rememberNavController()

            BiteScaffold(
                bottomBar = { BiteBottomBar(navController = navController, tabs = tabs) }
            ) { innerPaddingModifier ->
                BiteNavGraph(
                    navController = navController,
                    modifier = Modifier.padding(innerPaddingModifier)
                )
            }
        }
    }
}
