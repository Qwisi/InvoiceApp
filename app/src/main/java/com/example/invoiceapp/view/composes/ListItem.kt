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
fun SimpleInvoiceLinePreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ){
            SimpleListItem(
                overLineText = "status",
                headLine = "01-01-2024 / 00:00 AM",
                supportingText = "Name Surname"
            )
            SimpleListItem(
                overLineText = "\$price",
                headLine = "Item",
                supportingText = "Category"
            )
        }
    }
}

@Composable
fun SimpleListItem(
    modifier: Modifier = Modifier,
    overLineText: String,
    headLine: String,
    supportingText: String,
    onClick: () -> Unit = {},
    onLongPress: () -> Unit = {}
) {
    UnifiedLine(
        modifier = modifier,
        leftCompose = {
            Column {
                Text(
                    text = overLineText,
                    color = Color.Red,
                )
                Text( text = headLine )
                Text( text = supportingText )
            }
        },
        rightCompose = {
            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_list),
                contentDescription = "Open item icon"
            )
        },
        onClick = onClick,
        onLongPress = onLongPress
    )
}