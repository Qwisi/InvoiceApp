package com.example.invoiceapp.view.product

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.view.composes.StyledAddCard
import com.example.invoiceapp.view.composes.StyledOutlinedTextFieldProps
import com.example.invoiceapp.viewModel.CategoryViewModel

@Composable
fun AddCategoryOverlay(categoryViewModel: CategoryViewModel, onDismiss: () -> Unit) {
    StyledAddCard(
        modifier = Modifier.padding(horizontal = 20.dp),
        title = "Add New Category",
        styledOutlinedTextFieldProps = StyledOutlinedTextFieldProps(
            labelText = "Category Name",
            supportingText = "enter valid category (no digits)",
            validator = { it.contains(Regex("\\d")) },
            immediateValidation = true
        ),
        onConfirm = { newCategoryName ->
            categoryViewModel.insertCategory(Category(name = newCategoryName))
            onDismiss()
        },
        onDismiss = onDismiss
    )
}