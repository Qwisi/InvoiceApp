package com.example.invoiceapp.view.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.model.entities.measurement.Measurement
import com.example.invoiceapp.model.entities.product.Product
import com.example.invoiceapp.model.entities.product.ProductWithCM
import com.example.invoiceapp.view.composes.StyledListItem

@Preview
@Composable
fun ProductListPreview(){
    val productList = listOf(
        ProductWithCM(
            Product(
                title = "Apple",
                description = "sweet red apple",
                price = 23.5,
                measurementValue = 1.0,
                idCategory = 1,
                idMeasurement = 1
            ),
            Category(id = 1, name = "Vegetables"),
            Measurement(id = 1, unit = "kg")
        ),
        ProductWithCM(
            Product(
                title = "Candy",
                description = "sooo sweet",
                price = 12.3,
                measurementValue = 100.0,
                idCategory = 2,
                idMeasurement = 3
            ),
            Category(id = 2, name = "Sweets"),
            Measurement(id = 3, unit = "gr")
        ),
    )
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        ProductList(
            modifier = Modifier.padding(horizontal = 20.dp),
            searchValue = remember { mutableStateOf("") },
            productList = productList,
            changeBodyType = { },
            onProductClick = { },
            onProductLongPress = { }
        )
    }
}

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    searchValue: MutableState<String>,
    productList: List<ProductWithCM>,
    changeBodyType: () -> Unit,
    onProductClick: (ProductWithCM) -> Unit,
    onProductLongPress: (ProductWithCM) -> Unit,
){
    val filteredProducts = if (searchValue.value.isNotEmpty()) {
        productList.filter {
            it.product.title.contains(searchValue.value, ignoreCase = true)
        }
    } else {
        productList
    }
    LazyColumn(modifier = modifier.padding(vertical = 10.dp)) {
        items(filteredProducts) {
            StyledListItem(
                modifier = Modifier,
                overLineText = "$${it.product.price}",
                headLine = it.product.title,
                supportingText = it.category.name,
                onClick = { changeBodyType(); onProductClick(it) },
                onLongPress = { onProductLongPress(it) }
            )
        }
    }
}
