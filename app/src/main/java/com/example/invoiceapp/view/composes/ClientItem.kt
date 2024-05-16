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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.R

@Preview
@Composable
fun StyledClientItemPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ){
            StyledClientItem(
                clientName = "John Smith",
                onClick = {}
            )
            StyledClientItem(
                clientName = "Kim Joel",
                onClick = {}
            )
            StyledClientInvoiceItem(
                clientName = "John Smith",
                isChosen = remember { mutableStateOf(false) }
            )
            StyledClientInvoiceItem(
                clientName = "Kim Joel",
                isChosen = remember { mutableStateOf(false) }
            )
        }
    }
}

@Composable
fun StyledClientItem(
    modifier: Modifier = Modifier,
    clientName: String = "",
    onClick: () -> Unit = {},
    onLongPress: () -> Unit = {}
){
    UnifiedLine(
        modifier = modifier,
        leftCompose = {
            Text( text = clientName )
        },
        rightCompose ={
            Image(
                painter = painterResource(id = R.drawable.icon_arrow_list),
                contentDescription = "Open client icon"
            )
        },
        onClick = onClick,
        onLongPress = onLongPress
    )
}

@Composable
fun StyledClientInvoiceItem(
    modifier: Modifier = Modifier,
    clientName: String = "",
    isChosen: MutableState<Boolean>
){
    UnifiedLine(
        modifier = modifier,
        leftCompose = {
            Text( text = clientName )
        },
        rightCompose ={
            StyledCheckBox(isChecked = isChosen)
        }
    )
}