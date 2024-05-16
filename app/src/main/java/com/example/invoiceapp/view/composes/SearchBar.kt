package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.R
import com.example.invoiceapp.ui.theme.SubText50
import com.example.invoiceapp.ui.theme.SurfacePrimary
import com.example.invoiceapp.ui.theme.SurfaceSecondary


@Preview
@Composable
fun SearchBarPreview(){
    val searchText = remember { mutableStateOf("Active") }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Column {
            StyledSearchBar(modifier = Modifier.padding(20.dp))
            StyledSearchBar(
                modifier = Modifier.padding(20.dp),
                textValue = searchText
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledSearchBar(
    modifier: Modifier = Modifier,
    textValue: MutableState<String> = mutableStateOf("")
) {
    val isActive = textValue.value.isNotEmpty()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                if (isActive) SurfacePrimary else SurfaceSecondary,
                shape = RoundedCornerShape(30.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 5.dp)
            ,
            value = textValue.value,
            onValueChange = { textValue.value = it },
            placeholder = {
                Text("Search")
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = SubText50,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                if (isActive) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_close),
                        contentDescription = "Clear text",
                        modifier = Modifier
                            .clickable { textValue.value = "" }
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_search),
                        contentDescription = "Search icon",
                    )
                }
            }
        )
    }
}
