

package com.example.bite.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.bite.ui.MainDestinations.SNACK_ID_KEY
import com.example.bite.ui.home.HomeSections
import com.example.bite.ui.home.addHomeGraph
import com.example.bite.ui.snackdetail.SnackDetail

/**
 * Destinations used in the ([BiteApp]).
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val SNACK_DETAIL_ROUTE = "snack"
    const val SNACK_ID_KEY = "snackId"
}

@ExperimentalAnimationApi
@Composable
fun BiteNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = MainDestinations.HOME_ROUTE,
            startDestination = HomeSections.SHOP.route
        ) {
            addHomeGraph(
                onSnackSelected = { snackId: Long, from: NavBackStackEntry ->
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (from.lifecycleIsResumed()) {
                        navController.navigate("${MainDestinations.SNACK_DETAIL_ROUTE}/$snackId")
                    }
                },
                modifier = modifier
            )
        }
        composable(
            "${MainDestinations.SNACK_DETAIL_ROUTE}/{$SNACK_ID_KEY}",
            arguments = listOf(navArgument(SNACK_ID_KEY) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val snackId = arguments.getLong(SNACK_ID_KEY)
            SnackDetail(
                snackId = snackId,
                upPress = {
                    navController.navigateUp()
                }
            )
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
