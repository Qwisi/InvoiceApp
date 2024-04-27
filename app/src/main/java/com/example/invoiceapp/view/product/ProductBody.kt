package com.example.invoiceapp.view.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import com.example.invoiceapp.model.product.Product
import com.example.invoiceapp.model.product.ProductWithCM
import com.example.invoiceapp.view.composes.SimpleSearchBar
import com.example.invoiceapp.view.composes.StyledButton
import com.example.invoiceapp.viewModel.CategoryViewModel
import com.example.invoiceapp.viewModel.ProductViewModel

@Composable
fun ProductBody(
    innerPadding: PaddingValues,
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    onAddCategory: () -> Unit,
    onAddMeasurement: () -> Unit,
    onProductLongPress: (ProductWithCM) -> Unit
){
    val searchValue = remember { mutableStateOf("") }
    val showProductList = remember { mutableStateOf(true) }
    val chosenProduct = remember { mutableStateOf<ProductWithCM?>(null) }

    // Collect and convert to MutableList
    val productList = productViewModel.products.asFlow().collectAsState(initial = emptyList()).value
    val categoryList = categoryViewModel.categories.asFlow().collectAsState(initial = emptyList()).value
    val measurementList = productViewModel.measurements.asFlow().collectAsState(initial = emptyList()).value
    val productProps = productProps(chosenProduct.value)

    // State for selected category and measurement
    val categoryIndex = remember { mutableIntStateOf(0) }
    val measurementIndex = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showProductList.value) {
            SimpleSearchBar(textValue = searchValue)
            ProductList(
                modifier = Modifier.weight(1f),
                searchValue = searchValue,
                productList = productList,
                showProductList = showProductList,
                onProductChosen = { chosenProduct.value = it },
                onProductLongPress = { onProductLongPress(it) }
            )
        } else {
            ProductAdd(
                modifier = Modifier.weight(1f),
                productProps = productProps,
                categories = categoryList,
                measurements = measurementList,
                onCategorySelected = { categoryIndex.intValue = it },
                onAddCategory = onAddCategory,
                onMeasurementSelected = { measurementIndex.intValue = it },
                onAddMeasurement = onAddMeasurement
            )
        }
        StyledButton(
            modifier = Modifier.padding(vertical = 10.dp),
            text = if (showProductList.value) "+ Add product" else {
                if (chosenProduct.value == null) "Create" else "Save"
            }
        ) {
            creatingAndInsertingProduct(
                showProductList,
                productProps,
                categoryIndex,
                chosenProduct,
                measurementIndex,
                productViewModel
            )
        }
    }
}

private fun creatingAndInsertingProduct(
    showProductList: MutableState<Boolean>,
    productProps: ProductProperties,
    categoryIndex: MutableIntState,
    chosenProduct: MutableState<ProductWithCM?>,
    measurementIndex: MutableIntState,
    productViewModel: ProductViewModel
) {
    if (!showProductList.value) {
        val title = productProps.productTitleProps.textFieldValue.value
        val description = productProps.productDescriptionProps.textFieldValue.value
        val price = productProps.productPriceProps.textFieldValue.value.toDouble()
        val measurementValue =
            productProps.productMeasurementValueProps.textFieldValue.value.toDouble()

        // Use safe calls and checks to ensure indices are valid
        val categoryID =
            if (categoryIndex.intValue == 0) chosenProduct.value!!.category.id else categoryIndex.intValue
        val measurementID =
            if (measurementIndex.intValue == 0) chosenProduct.value!!.measurement.id else measurementIndex.intValue

        if (chosenProduct.value == null) {
            productViewModel.insert(
                Product(
                    title = title, description = description, price = price,
                    measurementValue = measurementValue, idCategory = categoryID,
                    idMeasurement = measurementID
                )
            )
        } else {
            val productToUpdate = Product(
                id = chosenProduct.value!!.product.id,
                title = title,
                description = description,
                price = price,
                measurementValue = measurementValue,
                idCategory = categoryID,
                idMeasurement = measurementID
            )
            productViewModel.update(
                productToUpdate
            )
        }
        chosenProduct.value = null
        categoryIndex.intValue = 0
        measurementIndex.intValue = 0
    }
    showProductList.value = !showProductList.value
}