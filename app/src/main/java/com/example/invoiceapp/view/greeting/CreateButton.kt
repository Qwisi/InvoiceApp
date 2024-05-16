package com.example.invoiceapp.view.greeting

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.view.composes.StyledButton
import com.example.invoiceapp.view.composes.StyledOutlinedTextFieldProps
import com.example.invoiceapp.viewModel.CategoryViewModel
import java.io.File
import java.io.FileOutputStream

@Composable
fun CreateButton(
    companyTextFieldProps: StyledOutlinedTextFieldProps,
    chosenCategories: SnapshotStateList<String>,
    imageUri: MutableState<Uri?>,
    imageUris: SnapshotStateList<Uri>,
    context: Context,
    viewModel: CategoryViewModel
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        StyledButton(
            modifier = Modifier.padding(10.dp),
            isEnabled = !companyTextFieldProps.isError.value && chosenCategories.size >= 1 && imageUri.value != null,
            text = "Create"
        ) {
            imageUris.first().let { uri ->
                context.saveImageToInternalStorage(uri, companyTextFieldProps.textFieldValue.value)
            }
            chosenCategories.forEach {
                viewModel.insertCategory(Category(name = it))
            }
        }
    }
}

private fun Context.saveImageToInternalStorage(uri: Uri?, logoName: String): String? {
    uri ?: return null
    val inputStream = contentResolver.openInputStream(uri) ?: return null
    val file = File(filesDir, "${logoName}_logo.jpg")
    val outputStream = FileOutputStream(file)
    inputStream.copyTo(outputStream)
    outputStream.close()
    inputStream.close()
    return file.absolutePath
}