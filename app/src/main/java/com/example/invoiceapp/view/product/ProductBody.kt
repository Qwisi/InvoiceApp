package com.example.invoiceapp.view.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import com.example.invoiceapp.model.entities.product.Product
import com.example.invoiceapp.model.entities.product.ProductWithCM
import com.example.invoiceapp.view.composes.StyledSearchBar
import com.example.invoiceapp.view.composes.StyledButton
import com.example.invoiceapp.viewModel.CategoryViewModel
import com.example.invoiceapp.viewModel.ProductViewModel

@Composable
fun ProductBody(
    innerPadding: PaddingValues,
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
    onCategoryAdd: () -> Unit,
    onMeasurementAdd: () -> Unit,
    onProductLongPress: (ProductWithCM) -> Unit
){
    val searchValue = remember { mutableStateOf("") }
    var currentBody by remember { mutableStateOf(BodyType.LIST) }
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
        when(currentBody) {
            BodyType.LIST -> {
                StyledSearchBar(textValue = searchValue)
                ProductList(
                    modifier = Modifier.weight(1f),
                    searchValue = searchValue,
                    productList = productList,
                    changeBodyType = { currentBody = BodyType.UPDATE },
                    onProductClick = { chosenProduct.value = it },
                    onProductLongPress = { onProductLongPress(it) }
                )
            }
            BodyType.ADD, BodyType.UPDATE -> {
                ProductAdd(
                    modifier = Modifier.weight(1f),
                    productProps = productProps,
                    categories = categoryList,
                    measurements = measurementList,
                    onCategorySelected = { categoryIndex.intValue = it },
                    onCategoryAdd = onCategoryAdd,
                    onMeasurementSelected = { measurementIndex.intValue = it },
                    onMeasurementAdd = onMeasurementAdd
                )
            }
        }
        StyledButton(
            modifier = Modifier.padding(vertical = 10.dp),
            text = when(currentBody) {
                BodyType.LIST -> { BodyType.LIST.buttonText }
                BodyType.ADD -> { BodyType.ADD.buttonText }
                BodyType.UPDATE -> { BodyType.UPDATE.buttonText }
            }
        ){
            when (currentBody){
                BodyType.LIST -> { currentBody = BodyType.ADD }
                BodyType.ADD, BodyType.UPDATE-> {
                    creatingAndInsertingProduct(
                        productProps,
                        categoryIndex,
                        chosenProduct,
                        measurementIndex,
                        productViewModel
                    )
                }
            }
        }
    }
}

private enum class BodyType(val buttonText: String){
    LIST("+ Add product"),
    ADD("Create"),
    UPDATE("Save")
}

private fun creatingAndInsertingProduct(
    productProps: ProductProperties,
    categoryIndex: MutableIntState,
    chosenProduct: MutableState<ProductWithCM?>,
    measurementIndex: MutableIntState,
    productViewModel: ProductViewModel
) {
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