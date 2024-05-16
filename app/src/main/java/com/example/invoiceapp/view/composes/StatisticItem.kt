package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.ui.theme.OnBackground
import com.example.invoiceapp.ui.theme.TextFieldValue

@Preview
@Composable
fun StatisticItemPreview(){

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ){
            StyledStatisticItem(
                headLine = "Today invoices",
                data = "47"
            )
            StyledStatisticItem(
                headLine = "Today average price",
                data = "568"
            )
            StyledStatisticItem(
                headLine = "Total invoices",
                data = "238"
            )
        }
    }
}

@Composable
fun StyledStatisticItem(
    modifier: Modifier = Modifier,
    headLine: String,
    data: String,
){
    UnifiedLine(
        modifier = modifier,
        leftCompose = {
            Text( text = headLine, style = TextFieldValue.copy(OnBackground) )

        },
        rightCompose ={
            Text(text = data, style = TextFieldValue.copy(OnBackground))
        }
    )
}