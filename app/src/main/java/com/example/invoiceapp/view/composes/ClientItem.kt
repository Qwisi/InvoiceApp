package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.R

@Preview
@Composable
fun SimpleClientItemPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ){
            SimpleClientItem(
                clientName = "John Smith",
                onClick = {}
            )
            SimpleClientItem(
                clientName = "Kim Joel",
                onClick = {}
            )
        }
    }
}

@Composable
fun SimpleClientItem(
    modifier: Modifier = Modifier,
    clientName: String = "",
    onClick: () -> Unit = {}
){
    UnifiedLine(
        modifier = modifier,
        leftCompose = {
            Text( text = clientName )
        },
        rightCompose ={
            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_list),
                contentDescription = "Open client icon"
            )
        },
        onClick = onClick
    )
}