package com.example.invoiceapp.view.product

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.entities.product.ProductWithCM
import com.example.invoiceapp.view.composes.StyledConfirmationCard
import com.example.invoiceapp.viewModel.ProductViewModel

@Composable
fun ConfirmProductDeleteOverlay(
    product: ProductWithCM,
    viewModel: ProductViewModel,
    onDismiss: () -> Unit
){
    StyledConfirmationCard(
        modifier = Modifier.padding(horizontal = 20.dp),
        action = "delete",
        page = "product",
        info = "${product.product.title} for price ${product.product.price} of category ${product.category.name}",
        onConfirm = {
            viewModel.delete(product)
            onDismiss()
        },
        onDismiss = onDismiss
    )
}