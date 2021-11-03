package com.example.bite.ui.home.cart

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bite.R
import com.example.bite.model.CartItem
import com.example.bite.model.MenuItemRepo
import com.example.bite.ui.components.BiteButton
import com.example.bite.ui.components.BiteDivider
import com.example.bite.ui.components.BiteSurface
import com.example.bite.ui.components.QuantitySelector
import com.example.bite.ui.components.MenuItemImage
import com.example.bite.ui.theme.AlphaNearOpaque
import com.example.bite.ui.theme.BiteTheme
import com.example.bite.ui.utils.formatPrice
import com.google.accompanist.insets.statusBarsHeight

@Composable
fun Cart(
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: CartViewModel = viewModel()
    val orderLines by viewModel.cartItems.collectAsState()
    Cart(
        cartItems = orderLines,
        removeSnack = viewModel::removeMenuItems,
        increaseItemCount = viewModel::increaseSnackCount,
        decreaseItemCount = viewModel::decreaseSnackCount,
        onSnackClick = onSnackClick,
        modifier = modifier
    )
}

@Composable
fun Cart(
    cartItems: List<CartItem>,
    removeSnack: (Long) -> Unit,
    increaseItemCount: (Long) -> Unit,
    decreaseItemCount: (Long) -> Unit,
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    BiteSurface(modifier = modifier.fillMaxSize()) {
        Box {
            CartContent(
                cartItems = cartItems,
                removeSnack = removeSnack,
                increaseItemCount = increaseItemCount,
                decreaseItemCount = decreaseItemCount,
                onSnackClick = onSnackClick,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            CheckoutBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun CartContent(
    cartItems: List<CartItem>,
    removeSnack: (Long) -> Unit,
    increaseItemCount: (Long) -> Unit,
    decreaseItemCount: (Long) -> Unit,
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val resources = LocalContext.current.resources
    val snackCountFormattedString = remember(cartItems.size, resources) {
        resources.getQuantityString(
            R.plurals.cart_order_count,
            cartItems.size, cartItems.size
        )
    }
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.statusBarsHeight(additional = 56.dp))
            Text(
                text = stringResource(R.string.cart_order_header, snackCountFormattedString),
                style = MaterialTheme.typography.h6,
                color = BiteTheme.colors.brand,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 24.dp, vertical = 4.dp)
                    .wrapContentHeight()
            )
        }
        items(cartItems) { orderLine ->
            CartItem(
                cartItem = orderLine,
                removeSnack = removeSnack,
                increaseItemCount = increaseItemCount,
                decreaseItemCount = decreaseItemCount,
                onSnackClick = onSnackClick
            )
        }
        item {
            SummaryItem(
                subtotal = cartItems.map { it.menuItem.price * it.count }.sum()
            )
        }

    }
}

@Composable
fun CartItem(
    cartItem: CartItem,
    removeSnack: (Long) -> Unit,
    increaseItemCount: (Long) -> Unit,
    decreaseItemCount: (Long) -> Unit,
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val snack = cartItem.menuItem
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSnackClick(snack.id) }
            .padding(horizontal = 24.dp)
    ) {
        val (divider, image, name, tag, priceSpacer, price, remove, quantity) = createRefs()
        createVerticalChain(name, tag, priceSpacer, price, chainStyle = ChainStyle.Packed)
        MenuItemImage(
            imageUrl = snack.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = snack.name,
            style = MaterialTheme.typography.subtitle1,
            color = BiteTheme.colors.textSecondary,
            modifier = Modifier.constrainAs(name) {
                start.linkTo(image.end, margin = 16.dp)
            }
        )
        IconButton(
            onClick = { removeSnack(snack.id) },
            modifier = Modifier
                .constrainAs(remove) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(top = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                tint = BiteTheme.colors.iconSecondary,
                contentDescription = stringResource(R.string.label_remove)
            )
        }
        Text(
            text = snack.tagline,
            style = MaterialTheme.typography.body1,
            color = BiteTheme.colors.textHelp,
            modifier = Modifier.constrainAs(tag) {
                start.linkTo(image.end, margin = 16.dp)
            }
        )
        Spacer(
            Modifier
                .height(8.dp)
                .constrainAs(priceSpacer) {
                    top.linkTo(tag.bottom)
                    bottom.linkTo(price.top)
                }
        )
        Text(
            text = formatPrice(snack.price),
            style = MaterialTheme.typography.subtitle1,
            color = BiteTheme.colors.textPrimary,
            modifier = Modifier.constrainAs(price) {
                start.linkTo(image.end, margin = 16.dp)
            }
        )
        QuantitySelector(
            count = cartItem.count,
            decreaseItemCount = { decreaseItemCount(snack.id) },
            increaseItemCount = { increaseItemCount(snack.id) },
            modifier = Modifier.constrainAs(quantity) {
                baseline.linkTo(price.baseline)
                end.linkTo(parent.end)
            }
        )
        BiteDivider(
            Modifier.constrainAs(divider) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
fun SummaryItem(
    subtotal: Long,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.cart_summary_header),
            style = MaterialTheme.typography.h6,
            color = BiteTheme.colors.brand,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .heightIn(min = 56.dp)
                .wrapContentHeight()
        )
        Row(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = stringResource(R.string.cart_subtotal_label),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
                    .alignBy(LastBaseline)
            )
            Text(
                text = formatPrice(subtotal),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        BiteDivider()
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            Text(
                text = stringResource(R.string.cart_total_label),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
                    .wrapContentWidth(Alignment.End)
                    .alignBy(LastBaseline)
            )
        }
        BiteDivider()
    }
}

@Composable
private fun CheckoutBar(modifier: Modifier = Modifier) {
    Column(
        modifier.background(
            BiteTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque)
        )
    ) {
        BiteDivider()
        Row {
            Spacer(Modifier.weight(1f))
            BiteButton(
                onClick = { /* todo */ },
                shape = RectangleShape,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.cart_checkout),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun CartPreview() {
    BiteTheme {
        Cart(
            cartItems = MenuItemRepo.getCart(),
            removeSnack = {},
            increaseItemCount = {},
            decreaseItemCount = {},
            onSnackClick = {}
        )
    }
}
