package com.example.invoiceapp.view.composes

import androidx.compose.foundation.Image
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
import com.example.invoiceapp.ui.theme.ConfirmGreen
import com.example.invoiceapp.ui.theme.ConfirmRed
import com.example.invoiceapp.ui.theme.FontOnBackground
import com.example.invoiceapp.ui.theme.SubText100

@Preview
@Composable
fun StyledListPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ){
            StyledListItem(
                overLineText = Status.Unpaid.textValue,
                headLine = "01-01-2024 / 00:00 AM",
                supportingText = "Name Surname"
            )
            StyledListItem(
                overLineText = Status.Paid.textValue,
                headLine = "01-01-2024 / 00:00 AM",
                supportingText = "Name Surname"
            )
            StyledListItem(
                overLineText = "\$price",
                headLine = "Product name",
                supportingText = "Category"
            )
        }
    }
}

enum class Status(val textValue: String){
    Paid("Paid"),
    Unpaid("Unpaid")
}

@Composable
fun StyledListItem(
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
                    color = if(Status.Paid.textValue == overLineText)
                        ConfirmGreen else ConfirmRed
                )
                Text(
                    text = headLine,
                    color = FontOnBackground
                )
                Text(
                    text = supportingText,
                    color = SubText100
                )
            }
        },
        rightCompose = {
            Image(
                painter = painterResource(id = R.drawable.icon_arrow_list),
                contentDescription = "Open item icon"
            )
        },
        onClick = onClick,
        onLongPress = onLongPress
    )
}