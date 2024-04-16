package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.R

@Preview
@Composable
fun SimpleSearchFilterPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        SimpleSearchFilterBar(
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
fun SimpleSearchFilterBar(
    modifier: Modifier = Modifier,
    textValue: MutableState<String> = mutableStateOf(""),
    onFilterClick: () -> Unit = {}
){
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        SimpleSearchBar(
            modifier = Modifier.weight(0.85f),
            textValue = textValue
        )
        Box(
            modifier = Modifier
                .weight(0.15f)
                .padding(end = 10.dp),
            contentAlignment = Alignment.CenterEnd
            ){
            Icon(
                modifier = Modifier.clickable { onFilterClick() },
                painter = painterResource(id = R.drawable.icon_filter),
                contentDescription = "Filter icon",
            )
        }
    }
}