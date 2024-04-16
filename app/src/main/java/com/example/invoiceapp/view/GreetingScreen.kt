package com.example.invoiceapp.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.view.composes.StyledCategoryManager
import com.example.invoiceapp.view.composes.StyledAttachBox
import com.example.invoiceapp.view.composes.StyledComponentOverlay
import com.example.invoiceapp.view.composes.CategoryManagerProps
import com.example.invoiceapp.view.composes.StyledAddCard
import com.example.invoiceapp.view.composes.StyledOutlinedTextField
import com.example.invoiceapp.view.composes.StyledButton
import com.example.invoiceapp.view.composes.StyledOutlinedTextFieldProps

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun GreetingScreen(

){
    var showAddCard by remember { mutableStateOf(false) }
    val categoryIndex  = remember { mutableIntStateOf(0) }
    val companyNameValue = remember { mutableStateOf("") }
    val categoryOptions = remember { mutableStateListOf("Fruits", "Electronics", "Clothes") }
    val chosenCategories = remember { mutableStateListOf("Fruits", "Clothes") }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center) {
                        Text("Create account")
                    }
                }
            )
        },
        bottomBar = {
            Box (
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                StyledButton(
                    modifier = Modifier.padding(10.dp),
                    text = "Create"
                ) {

                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {

            StyledOutlinedTextField(
                modifier = Modifier,
                props = StyledOutlinedTextFieldProps(
                    textFieldValue = companyNameValue,
                    labelText = "Company name"
                )
            )
            StyledCategoryManager(
                modifier = Modifier,
                label = "Category name",
                props = CategoryManagerProps(
                    selectedIndex = categoryIndex,
                    options = categoryOptions,
                    chosenCategories = chosenCategories,
                    onCreateCategory = { showAddCard = true }
                )
            )
            StyledAttachBox(
                modifier = Modifier,
                onClick = {}
            )
        }
    }

    if(showAddCard){
        StyledComponentOverlay {
            StyledAddCard(
                modifier = Modifier.padding(horizontal = 20.dp),
                onConfirm = { newCategory ->
                    if (!categoryOptions.contains(newCategory)) {
                        categoryOptions.add(newCategory)
                    }
                    if (!chosenCategories.contains(newCategory)) {
                        chosenCategories.add(newCategory)
                    }
                    categoryIndex.intValue = categoryOptions.size - 1
                    showAddCard = false
                },
                onDismiss = { showAddCard = false }
            )
        }
    }
}