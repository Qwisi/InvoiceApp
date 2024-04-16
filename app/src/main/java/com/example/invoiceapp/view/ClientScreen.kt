package com.example.invoiceapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.invoiceapp.view.composes.SimpleClientItem
import com.example.invoiceapp.view.composes.SimpleSearchFilterBar
import com.example.invoiceapp.view.composes.SimpleTopBar
import com.example.invoiceapp.view.composes.StyledButton
import com.example.invoiceapp.view.composes.StyledNavigationBar
import com.example.invoiceapp.view.composes.StyledOutlinedTextField
import com.example.invoiceapp.view.composes.StyledOutlinedTextFieldProps

@Preview
@Composable
fun ClientScreenPreview(){
    //ClientScreen()
}

@Composable
fun ClientScreen(
    navController: NavController,
    //viewModel: ClientViewModel
){
    //val customers by viewModel.clients.ob
    val showClientList = remember { mutableStateOf(true) }
    val clientProps = clientProps()

    val searchValue = remember { mutableStateOf("")  }
    val clientList = mutableListOf(
        "John Smith",
        "Kim Joel",
        "Samantha Carter",
        "Daniel Jackson",
        "Teal'c Johnson",
        "Jack O'Neill",
        "Rodney McKay",
        "Jean-Luc Picard",
        "James T. Kirk",
        "Han Solo",
        "Leia Organa",
        "Luke Skywalker",
        "Bruce Wayne",
        "Clark Kent",
        "Diana Prince",
        "Tony Stark",
        "Steve Rogers",
        "Natasha Romanoff",
        "Peter Parker",
        "Bruce Banner",
        "Wanda Maximoff",
        "Stephen Strange",
        "Carol Danvers",
        "T'Challa Udaku",
    )
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Client"
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
            SimpleSearchFilterBar(
                textValue = searchValue,
                onFilterClick = {
                    // on filter icon click
                }
            )

            if(showClientList.value){
                BodyClientList(modifier = Modifier.weight(1f), searchValue = searchValue, clientList = clientList)
            }else{
                BodyClientAdd(modifier = Modifier.weight(1f), clientProps = clientProps)
            }

            StyledButton(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                text = if(showClientList.value) "+ Add client" else "Create"
            ) {
                if(!showClientList.value){
                    // add data to database
                    val clientName = clientProps.clientNameProps.textFieldValue.value
                    val clientSurname = clientProps.clientSurnameProps.textFieldValue.value
                    clientList.add("$clientName $clientSurname")
                }
                showClientList.value = !showClientList.value
            }
        }
    }
}

@Composable
fun BodyClientAdd(
    modifier: Modifier = Modifier,
    clientProps: ClientProperties
){
    LazyColumn(
        modifier = modifier.padding(vertical = 10.dp)
    ) {
        item {
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                StyledOutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp),
                    props = clientProps.clientNameProps
                )
                StyledOutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp),
                    props = clientProps.clientSurnameProps
                )
            }
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientPhoneProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientCountryProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientCityProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientStreetProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientHouseNumberProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientPostCodeProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientDiscountProps
            )
        }
        item {
            StyledOutlinedTextField(
                props = clientProps.clientSpecialNoteProps
            )
        }
    }
}

@Composable
fun BodyClientList(
    modifier: Modifier = Modifier,
    searchValue: MutableState<String>,
    clientList: List<String>
){
    LazyColumn(modifier = modifier.padding(vertical = 10.dp)) {
        val filteredItems = if (searchValue.value.isNotEmpty()) {
            clientList.filter {
                it.contains(searchValue.value, ignoreCase = true)
            }
        } else {
            clientList
        }
        items(filteredItems) { item ->
            SimpleClientItem(
                clientName = item,
                onClick = {
                    // handle click
                }
            )
        }
    }
}

data class ClientProperties(
    val clientNameProps: StyledOutlinedTextFieldProps,
    val clientSurnameProps: StyledOutlinedTextFieldProps,
    val clientPhoneProps: StyledOutlinedTextFieldProps,
    val clientCountryProps: StyledOutlinedTextFieldProps,
    val clientCityProps: StyledOutlinedTextFieldProps,
    val clientStreetProps: StyledOutlinedTextFieldProps,
    val clientHouseNumberProps: StyledOutlinedTextFieldProps,
    val clientPostCodeProps: StyledOutlinedTextFieldProps,
    val clientDiscountProps: StyledOutlinedTextFieldProps,
    val clientSpecialNoteProps: StyledOutlinedTextFieldProps
)

@Composable
fun clientProps(): ClientProperties {
    val clientName = remember { mutableStateOf("") }
    val clientSurname = remember { mutableStateOf("") }
    val clientPhone = remember { mutableStateOf("") }
    val clientCountry = remember { mutableStateOf("") }
    val clientCity = remember { mutableStateOf("") }
    val clientStreet = remember { mutableStateOf("") }
    val clientHouseNumber = remember { mutableStateOf("") }
    val clientPostCode = remember { mutableStateOf("") }
    val clientDiscount = remember { mutableStateOf("") }
    val clientSpecialNote = remember { mutableStateOf("") }

    return ClientProperties(
        clientNameProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientName,
            labelText = "Name",
            placeholderText = "John"
        ),
        clientSurnameProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientSurname,
            labelText = "Surname",
            placeholderText = "Smith"
        ),
        clientPhoneProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientPhone,
            labelText = "Phone Number",
            placeholderText = "+1 234 567 8900"
        ),
        clientCountryProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientCountry,
            labelText = "Country",
            placeholderText = "USA"
        ),
        clientCityProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientCity,
            labelText = "City",
            placeholderText = "New York"
        ),
        clientStreetProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientStreet,
            labelText = "Street",
            placeholderText = "5th Avenue"
        ),
        clientHouseNumberProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientHouseNumber,
            labelText = "House Number",
            placeholderText = "101"
        ),
        clientPostCodeProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientPostCode,
            labelText = "Post Code",
            placeholderText = "10001"
        ),
        clientDiscountProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientDiscount,
            labelText = "Discount",
            placeholderText = "10%"
        ),
        clientSpecialNoteProps = StyledOutlinedTextFieldProps(
            textFieldValue = clientSpecialNote,
            labelText = "Special Note",
            placeholderText = "Enter any special instructions"
        )
    )
}
