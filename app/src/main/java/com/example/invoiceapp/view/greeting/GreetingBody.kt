package com.example.invoiceapp.view.greeting

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.view.composes.CategoryManagerProps
import com.example.invoiceapp.view.composes.LogoGrid
import com.example.invoiceapp.view.composes.StyledAttachBox
import com.example.invoiceapp.view.composes.StyledCategoryManager
import com.example.invoiceapp.view.composes.StyledOutlinedTextField
import com.example.invoiceapp.view.composes.StyledOutlinedTextFieldProps

@Composable
fun GreetingBody(
    innerPadding: PaddingValues,
    companyTextFieldProps: StyledOutlinedTextFieldProps,
    categoryIndex: MutableIntState,
    categoryOptions: SnapshotStateList<String>,
    chosenCategories: SnapshotStateList<String>,
    showAddCard: MutableState<Boolean>,
    imageUri: MutableState<Uri?>,
    imageUris: SnapshotStateList<Uri>
) {
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUri.value = result.data?.data
            imageUri.value.let {
                //imageUris?.removeLast()
                imageUris.add(it!!)
            }
        }
    }
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        StyledOutlinedTextField(
            modifier = Modifier,
            props = companyTextFieldProps
        )
        StyledCategoryManager(
            modifier = Modifier,
            label = "Category name",
            props = CategoryManagerProps(
                selectedIndex = categoryIndex,
                options = categoryOptions,
                chosenCategories = chosenCategories,
                onCreateCategory = { showAddCard.value = true }
            )
        )
        StyledAttachBox(
            modifier = Modifier,
            onClick = {
                pickImageLauncher.launch(Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                })
            }
        )
        LogoGrid(
            modifier = Modifier,
            imageUris = imageUris
        )
    }
}