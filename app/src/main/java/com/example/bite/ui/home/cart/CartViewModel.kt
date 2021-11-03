

package com.example.bite.ui.home.cart

import androidx.lifecycle.ViewModel
import com.example.bite.model.CartItem
import com.example.bite.model.MenuItemRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Holds the contents of the cart and allows changes to it.
 *
 * TODO: Move data to Repository so it can be displayed and changed consistently throughout the app.
 */
class CartViewModel : ViewModel() {
    private val _cartItems: MutableStateFlow<List<CartItem>> = MutableStateFlow(MenuItemRepo.getCart())
    val cartItems: StateFlow<List<CartItem>> get() = _cartItems

    fun removeMenuItems(snackId: Long) {
        _cartItems.value = _cartItems.value.filter { it.menuItem.id != snackId }
    }

    fun increaseSnackCount(snackId: Long) {
        val currentCount = _cartItems.value.first { it.menuItem.id == snackId }.count
        updateSnackCount(snackId, currentCount + 1)
    }

    fun decreaseSnackCount(snackId: Long) {
        val currentCount = _cartItems.value.first { it.menuItem.id == snackId }.count
        if (currentCount == 1) {
            // remove snack from cart
            removeMenuItems(snackId)
        } else {
            // update quantity in cart
            updateSnackCount(snackId, currentCount - 1)
        }
    }

    private fun updateSnackCount(snackId: Long, count: Int) {
        _cartItems.value = _cartItems.value.map {
            if (it.menuItem.id == snackId) {
                it.copy(count = count)
            } else {
                it
            }
        }
    }
}
