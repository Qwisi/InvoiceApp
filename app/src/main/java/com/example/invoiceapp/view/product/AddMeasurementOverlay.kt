package com.example.invoiceapp.view.product

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.entities.measurement.Measurement
import com.example.invoiceapp.view.composes.StyledAddCard
import com.example.invoiceapp.view.composes.StyledOutlinedTextFieldProps
import com.example.invoiceapp.viewModel.ProductViewModel

@Composable
fun AddMeasurementOverlay(productViewModel: ProductViewModel, onDismiss: () -> Unit) {
    StyledAddCard(
        modifier = Modifier.padding(horizontal = 20.dp),
        title = "Add New Measurement Unit",
        styledOutlinedTextFieldProps = StyledOutlinedTextFieldProps(
            labelText = "Measurement Unit",
            supportingText = "enter valid measurement unit (no digits)",
            validator = { it.contains(Regex("\\d")) },
            immediateValidation = true
        ),
        onConfirm = { newMeasurementName ->
            productViewModel.insert(Measurement(unit = newMeasurementName))
            onDismiss()
        },
        onDismiss = onDismiss
    )
}