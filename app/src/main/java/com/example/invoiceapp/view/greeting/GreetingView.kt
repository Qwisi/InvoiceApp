package com.example.invoiceapp.view.greeting

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.ApplicationDB
import com.example.invoiceapp.model.repository.CategoryRepository
import com.example.invoiceapp.view.composes.StyledComponentOverlay
import com.example.invoiceapp.view.composes.StyledAddCard
import com.example.invoiceapp.view.composes.StyledOutlinedTextFieldProps
import com.example.invoiceapp.viewModel.CategoryViewModel

@Preview
@Composable
fun GreetingViewPreview(){
    val tempDb = ApplicationDB.getPreviewDatabase(LocalContext.current)
    val categoryDao = tempDb.categoryDao()
    val categoryRepository = CategoryRepository(categoryDao)
    val viewModel = CategoryViewModel(categoryRepository)
    
    GreetingView(viewModel = viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingView(
    viewModel: CategoryViewModel
) {
    val showAddCard = remember { mutableStateOf(false) }
    val categoryIndex = remember { mutableIntStateOf(0) }
    val categoryOptions = remember { mutableStateListOf("Fruits", "Electronics", "Clothes") }
    val chosenCategories = remember { mutableStateListOf("Fruits", "Clothes") }
    val companyTextFieldProps = StyledOutlinedTextFieldProps(
        labelText = "Company name",
        supportingText = "enter valid company name (no digits)",
        validator = { string -> string.contains(Regex("\\d")) },
        immediateValidation = true
    )

    val context = LocalContext.current
    val imageUris = remember { mutableStateListOf<Uri>() }
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box( modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) { Text("Create account") }
                }
            )
        },
        bottomBar = {
            CreateButton(
                companyTextFieldProps, chosenCategories,
                imageUri, imageUris, context, viewModel
            )
        }
    ) { innerPadding ->
        GreetingBody(
            innerPadding, companyTextFieldProps,
            categoryIndex, categoryOptions,
            chosenCategories, showAddCard,
            imageUri, imageUris
        )
    }

    AddCategoryOverlay(showAddCard, categoryOptions, chosenCategories, categoryIndex)
}

@Composable
private fun AddCategoryOverlay(
    showAddCard: MutableState<Boolean>,
    categoryOptions: SnapshotStateList<String>,
    chosenCategories: SnapshotStateList<String>,
    categoryIndex: MutableIntState
) {
    if (showAddCard.value) {
        StyledComponentOverlay {
            StyledAddCard(
                modifier = Modifier.padding(horizontal = 20.dp),
                title = "Add new category",
                styledOutlinedTextFieldProps = StyledOutlinedTextFieldProps(
                    labelText = "Category",
                    supportingText = "enter valid category (no digits)",
                    validator = { it.contains(Regex("\\d")) },
                    immediateValidation = true
                ),
                onConfirm = { newCategory ->
                    if (!categoryOptions.contains(newCategory)) {
                        categoryOptions.add(newCategory)
                    }
                    if (!chosenCategories.contains(newCategory)) {
                        chosenCategories.add(newCategory)
                    }
                    categoryIndex.intValue = categoryOptions.size - 1
                    showAddCard.value = false
                },
                onDismiss = { showAddCard.value = false }
            )
        }
    }
}