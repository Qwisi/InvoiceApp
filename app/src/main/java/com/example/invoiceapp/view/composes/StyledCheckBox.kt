package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.ui.theme.IconOnBackground
import com.example.invoiceapp.ui.theme.Primary100

@Preview
@Composable
fun StyledCheckBoxPreview(){
    Box(modifier = Modifier
        .size(50.dp, 50.dp)
        .background(Color.White)){
        StyledCheckBox(
            isChecked = remember { mutableStateOf(false) }
        )
    }
}
@Composable
fun StyledCheckBox(
    isChecked: MutableState<Boolean>
){
    Checkbox(
        checked = isChecked.value,
        onCheckedChange = { isChecked.value = it },
        colors = CheckboxDefaults.colors(
            checkedColor = Primary100,
            uncheckedColor = IconOnBackground,
            checkmarkColor = Color.White
        )
    )
}