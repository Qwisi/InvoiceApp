package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.invoiceapp.ui.theme.FontOnBackground
import com.example.invoiceapp.ui.theme.IconOnBackground
import com.example.invoiceapp.ui.theme.TextFieldValue

@Preview
@Composable
fun ChipPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Chip(
            modifier = Modifier,
            text = "Fruit",
            onClick = {}
        )
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
){
    Surface (
        color = IconOnBackground,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = text,
            style = TextFieldValue.copy(letterSpacing = 0.05.em),
            color = FontOnBackground,
            modifier = Modifier.padding(15.dp, 7.5.dp)
        )
    }
}