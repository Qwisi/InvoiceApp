package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.R
import com.example.invoiceapp.ui.theme.HeadLineRobotoRegular

@Preview
@Composable
fun SimpleTopBarPreview(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        SimpleTopBar(
            modifier = Modifier
                .padding(end = 16.dp, bottom = 5.dp),
            price = 80.0
        )
    }
}

@Composable
fun SimpleTopBar(
    modifier: Modifier = Modifier,
    title: String = "Current screen",
    price: Double = 0.0,
    onBackClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_back),
            contentDescription = "Back icon",
            modifier = Modifier
                .padding(end = 10.dp)
                .clickable(onClick = onBackClick)
        )
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = HeadLineRobotoRegular
        )
        if (price > 0.0) {
            Text(
                text = "Price: ",
                modifier = Modifier.padding(start = 10.dp),
                style = HeadLineRobotoRegular
            )
            Text(
                text = price.toString(),
                style = HeadLineRobotoRegular.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}