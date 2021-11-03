package com.example.bite.ui.home.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.bite.model.Restaurant
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun Shop(
    //restaurants: List<Restaurant>,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        val listState = rememberLazyListState()
        val scope = rememberCoroutineScope()

        //Spacer(modifier = Modifier.statusBarsPadding())

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            //val grouped = restaurants.groupBy { it.Name[0] }

//            grouped.forEach { initial, restaurants ->
//                items(restaurants) { restaurant ->
//                    RestaurantListItem(
//                        restaurant = restaurant,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                }
//            }
        }

        val showButton = listState.firstVisibleItemIndex > 0
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            IconButton(
                onClick = {
                    scope.launch { listState.scrollToItem(0) }
                }
            ){
                Icon(
                    Icons.Filled.ArrowCircleUp,
                    contentDescription = "Localized description",
                )
            }
        }
        }
    }

//@Composable
//fun RestaurantListItem(
//    restaurant: Restaurant,
//    modifier: Modifier
//) {
//
//}
