package com.example.invoiceapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.invoiceapp.view.composes.SimpleListItem
import com.example.invoiceapp.view.composes.SimpleSearchBar
import com.example.invoiceapp.view.composes.SimpleTopBar
import com.example.invoiceapp.view.composes.StyledButton
import com.example.invoiceapp.view.composes.StyledNavigationBar

@Composable
fun InvoiceScreen(
    navController: NavController
){
    val searchValue = remember { mutableStateOf("")  }
    val listItems = listOf(
        Triple("In progress", "01-01-2024 / 10:30 AM", "Alex Johnson"),
        Triple("Completed", "05-01-2024 / 03:45 PM", "Morgan Yu"),
        Triple("Canceled", "12-01-2024 / 08:00 AM", "Sam Daniels"),
        Triple("Pending", "20-01-2024 / 01:15 PM", "Jamie Smith"),
        Triple("In review", "22-01-2024 / 11:20 AM", "Chris Wong"),
        Triple("On hold", "28-01-2024 / 09:30 AM", "Terry Brooks"),
        Triple("In progress", "03-02-2024 / 04:50 PM", "Robin Chase"),
        Triple("Completed", "10-02-2024 / 02:25 PM", "Pat Lee"),
        Triple("Canceled", "14-02-2024 / 10:00 AM", "Dana Kay"),
        Triple("Pending", "18-02-2024 / 07:40 PM", "Jordan Kip")
    )
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Invoice"
            ){
                // on back arrow click
            }
        },
        bottomBar = {
            StyledNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 15.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            SimpleSearchBar(
                textValue = searchValue
            )
            LazyColumn(
                modifier = Modifier.weight(1f).padding(vertical = 10.dp)
            ) {
                val filteredItems = if (searchValue.value.isNotEmpty()) {
                    listItems.filter {
                        it.third.contains(searchValue.value, ignoreCase = true)
                    }
                } else {
                    listItems
                }
                items(filteredItems) { item ->
                    SimpleListItem(
                        modifier = Modifier.padding(vertical = 5.dp),
                        overLineText = item.first,
                        headLine = item.second,
                        supportingText = item.third
                    ){
                        // on item in list click
                    }
                }
            }
            StyledButton(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                text = "+ Create invoice"
            ) {
                // on create invoice button click
            }
        }
    }
}