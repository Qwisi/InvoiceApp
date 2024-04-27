package com.example.invoiceapp.view.product

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.product.ProductWithCM
import com.example.invoiceapp.view.composes.SimpleListItem

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    searchValue: MutableState<String>,
    productList: List<ProductWithCM>,
    showProductList: MutableState<Boolean>,
    onProductChosen: (ProductWithCM) -> Unit,
    onProductLongPress: (ProductWithCM) -> Unit,
){
    LazyColumn(modifier = modifier.padding(vertical = 10.dp)) {
        val filteredProducts = if (searchValue.value.isNotEmpty()) {
            productList.filter { product ->
                product.product.title.contains(searchValue.value, ignoreCase = true)
            }
        } else {
            productList
        }

        items(filteredProducts) { product ->
            SimpleListItem(
                modifier = Modifier,
                overLineText = "$${product.product.price}",
                headLine = product.product.title,
                supportingText = product.category.category,
                onClick = {
                    showProductList.value = false
                    onProductChosen(product)
                },
                onLongPress = { onProductLongPress(product) }
            )
        }
    }
}
