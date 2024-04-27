package com.example.invoiceapp.view.product

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.category.Category
import com.example.invoiceapp.model.measurement.Measurement
import com.example.invoiceapp.model.product.ProductWithCM
import com.example.invoiceapp.view.composes.StyledDropDownMenu
import com.example.invoiceapp.view.composes.StyledDropDownMenuProps
import com.example.invoiceapp.view.composes.StyledOutlinedTextField
import com.example.invoiceapp.view.composes.StyledOutlinedTextFieldProps

@Composable
fun ProductAdd(
    modifier: Modifier = Modifier,
    productProps: ProductProperties,
    categories: List<Category>,
    measurements: List<Measurement>,
    onCategorySelected: (Int) -> Unit,
    onAddCategory: () -> Unit,
    onMeasurementSelected: (Int) -> Unit,
    onAddMeasurement: () -> Unit
){
    val categoryIndex = remember { mutableIntStateOf(categories.indexOfFirst { it.id == productProps.productCategoryID }) }
    val measurementIndex = remember { mutableIntStateOf(measurements.indexOfFirst { it.id == productProps.productMeasurementID }) }

    LazyColumn(
        modifier = modifier.padding(vertical = 10.dp)
    ) {
        item {
            StyledOutlinedTextField(
                props = productProps.productTitleProps
            )
        }
        item {
            StyledOutlinedTextField(
                modifier = Modifier.heightIn(min = 150.dp),
                props = productProps.productDescriptionProps
            )
        }
        item {
            StyledDropDownMenu(
                modifier = Modifier.padding(bottom = 10.dp),
                labelText = "Category",
                props = StyledDropDownMenuProps(
                    options = categories.map { it.category }.toMutableList(),
                    selectedIndex = categoryIndex,
                    onCreateCategory = onAddCategory,
                    onCategorySelected = { selectedOption ->
                        val selectedCategoryIndex = categories.indexOfFirst { it.category == selectedOption }
                        categoryIndex.intValue = selectedCategoryIndex
                        onCategorySelected(categories[selectedCategoryIndex].id)
                    }
                )
            )
        }
        item {
            StyledOutlinedTextField(
                props = productProps.productPriceProps
            )
        }
        item {
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                StyledOutlinedTextField(
                    modifier = Modifier
                        .weight(0.6f)
                        .padding(end = 5.dp),
                    props = productProps.productMeasurementValueProps
                )
                StyledDropDownMenu(
                    labelText = "Measurement units",
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(start = 5.dp),
                    props = StyledDropDownMenuProps(
                        options = measurements.map { it.unit }.toMutableList(),
                        selectedIndex = measurementIndex,
                        onCreateCategory = onAddMeasurement,
                        onCategorySelected = { selectedOption ->
                            val selectedMeasurementIndex = measurements.indexOfFirst { it.unit == selectedOption }
                            measurementIndex.intValue = selectedMeasurementIndex
                            onMeasurementSelected(measurements[selectedMeasurementIndex].id)
                        }
                    )
                )
            }
        }
    }
}

data class ProductProperties(
    val productTitleProps: StyledOutlinedTextFieldProps,
    val productDescriptionProps: StyledOutlinedTextFieldProps,
    val productPriceProps: StyledOutlinedTextFieldProps,
    val productMeasurementValueProps: StyledOutlinedTextFieldProps,
    val productCategoryID: Int,
    val productMeasurementID: Int,
)

@Composable
fun productProps(
    product: ProductWithCM? = null,
): ProductProperties {
    val productTitle = remember(product?.product?.title) { mutableStateOf(product?.product?.title ?: "") }
    val productDescription = remember(product?.product?.description) { mutableStateOf(product?.product?.description ?: "") }
    val productPrice = remember(product?.product?.price) { mutableStateOf(product?.product?.price?.toString() ?: "") }
    val productMeasurementValue = remember(product?.product?.measurementValue) { mutableStateOf(product?.product?.measurementValue?.toString() ?: "") }

    return ProductProperties(
        productTitleProps = StyledOutlinedTextFieldProps(
            textFieldValue = productTitle,
            labelText = "Title",
            placeholderText = "Enter product name",
            //supportingText = "enter valid title (no digits)",
            //validator = {string -> string.contains(Regex("\\d")) },
            //immediateValidation = true
        ),
        productDescriptionProps = StyledOutlinedTextFieldProps(
            textFieldValue = productDescription,
            labelText = "Description",
            placeholderText = "Enter product description",
            singleLine = false
        ),
        productPriceProps = StyledOutlinedTextFieldProps(
            textFieldValue = productPrice,
            labelText = "Price",
            placeholderText = "Enter product price"
        ),
        productMeasurementValueProps = StyledOutlinedTextFieldProps(
            textFieldValue = productMeasurementValue,
            labelText = "Measurement value",
            placeholderText = "Enter measurement value"
        ),
        productCategoryID = product?.category?.id ?: 0,
        productMeasurementID = product?.measurement?.id ?: 0
    )
}