package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.R

@Preview
@Composable
fun ProductItemPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ){
            ProductItem(
                quantity = remember { mutableIntStateOf(0) },
                isChosen = remember { mutableStateOf(false) }
            )
            ProductItem(
                quantity = remember { mutableIntStateOf(0) },
                isChosen = remember { mutableStateOf(false) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    productName: String = "Product name",
    quantity: MutableIntState,
    isChosen: MutableState<Boolean>,
){

    UnifiedLine(
        modifier = modifier,
        leftCompose = {
            StyledCheckBox(isChecked = isChosen)
            Spacer(Modifier.width(8.dp))
            Text(text = productName)
        },
        rightCompose = {
            IconButton(onClick = {
                if (isChosen.value) {
                    val currentQuantity = quantity.intValue
                    if (currentQuantity > 0) quantity.intValue = (currentQuantity - 1)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_list),
                    contentDescription = "Decrease quantity"
                )
            }
            OutlinedTextField(
                value = quantity.intValue.toString(),
                onValueChange = { newValue ->
                    if (isChosen.value) {
                        if (newValue.toIntOrNull() != null || newValue.isBlank()) {
                            quantity.intValue = newValue.ifBlank { "0" }.toInt()
                        }
                    }
                },
                modifier = Modifier
                    .width(60.dp)
                    .align(Alignment.CenterVertically),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                enabled = isChosen.value,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    disabledTextColor = Color.Gray,
                )
            )
            IconButton(onClick = {
                if (isChosen.value) {
                    val currentQuantity = quantity.intValue
                    quantity.intValue = (currentQuantity + 1)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_list),
                    contentDescription = "Increase quantity"
                )
            }
        }
    )
}