package com.example.bite.model

import androidx.compose.runtime.Immutable

@Immutable
data class SnackCollection(
    val id: Long,
    val name: String,
    val snacks: List<MenuItem>,
    val type: CollectionType = CollectionType.Normal
)

enum class CollectionType { Normal, Highlight }

/**
 * A fake repo
 */
object MenuItemRepo {
    fun getSnack(snackId: Long) = snacks.find { it.id == snackId }!!
    fun getRelated(@Suppress("UNUSED_PARAMETER") snackId: Long) = relatedMenuItems
    fun getCart() = cart
}

//Static data - need to get from  apis from here or massage on front end
private val topChart = SnackCollection(
    id = 2L,
    name = "Popular on Bite",
    snacks = snacks.subList(14, 19)
)

private val relatedMenuItems = listOf(
    topChart
)

private val cart = listOf(
    CartItem(snacks[4], 2),
    CartItem(snacks[6], 3),
    CartItem(snacks[8], 1)
)

@Immutable
data class CartItem(
    val menuItem: MenuItem,
    val count: Int
)
