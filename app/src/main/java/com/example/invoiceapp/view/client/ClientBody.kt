package com.example.invoiceapp.view.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.entities.client.Client
import com.example.invoiceapp.view.composes.StyledSearchBar
import com.example.invoiceapp.view.composes.StyledButton
import com.example.invoiceapp.viewModel.ClientViewModel

@Composable
fun ClientBody(
    innerPadding: PaddingValues,
    viewModel: ClientViewModel,
    onClientLongPress: (Client) -> Unit
) {
    val clientList by viewModel.clients.observeAsState(initial = emptyList())
    val showClientList = remember { mutableStateOf(true) }
    val chosenClient = remember { mutableStateOf<Client?>(null) }
    val clientProps = clientProps(chosenClient.value)
    val searchValue = remember { mutableStateOf("")  }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showClientList.value) {
            StyledSearchBar(
                textValue = searchValue
            )
            ClientList(
                modifier = Modifier.weight(1f),
                searchValue = searchValue,
                clientList = clientList,
                showClientList = showClientList,
                onClientChosen = { chosenClient.value = it },
                onClientLongPress = { onClientLongPress(it)}
            )
        } else {
            ClientAdd(
                modifier = Modifier.weight(1f),
                clientProps = clientProps
            )
        }
        StyledButton(
            modifier = Modifier.padding(vertical = 10.dp),
            text = if (showClientList.value) "+ Add client" else {
                if (chosenClient.value == null) "Create" else "Save"
            },
            isEnabled = showClientList.value || (
                            clientProps.clientNameProps.isError.value &&
                            clientProps.clientSurnameProps.isError.value &&
                            clientProps.clientPhoneProps.isError.value &&
                            clientProps.clientCountryProps.isError.value &&
                            clientProps.clientCityProps.isError.value &&
                            clientProps.clientStreetProps.isError.value &&
                            clientProps.clientHouseNumberProps.isError.value &&
                            clientProps.clientPostCodeProps.isError.value &&
                            clientProps.clientDiscountProps.isError.value &&
                            clientProps.clientSpecialNoteProps.isError.value
                    )
        ) {
            creatingAndInsertingClient(showClientList, clientProps, chosenClient, viewModel)
        }
    }
}

private fun creatingAndInsertingClient(
    showClientList: MutableState<Boolean>,
    clientProps: ClientProperties,
    chosenClient: MutableState<Client?>,
    viewModel: ClientViewModel
) {
    if (!showClientList.value) {
        val name = clientProps.clientNameProps.textFieldValue.value
        val surname = clientProps.clientSurnameProps.textFieldValue.value
        val phoneNumber = clientProps.clientPhoneProps.textFieldValue.value
        val country = clientProps.clientCountryProps.textFieldValue.value
        val city = clientProps.clientCityProps.textFieldValue.value
        val street = clientProps.clientStreetProps.textFieldValue.value
        val houseNumber = clientProps.clientHouseNumberProps.textFieldValue.value.toInt()
        val postCode = clientProps.clientPostCodeProps.textFieldValue.value
        val discount = clientProps.clientDiscountProps.textFieldValue.value.toDouble()
        val specialNotes = clientProps.clientSpecialNoteProps.textFieldValue.value

        if (chosenClient.value == null) {
            viewModel.insert(
                Client(
                    name = name, surname = surname, phoneNumber = phoneNumber,
                    country = country, city = city, street = street, houseNumber = houseNumber,
                    postCode = postCode, discount = discount, specialNotes = specialNotes,
                )
            )
        } else {
            viewModel.update(
                Client(
                    id = chosenClient.value!!.id,
                    name = name,
                    surname = surname,
                    phoneNumber = phoneNumber,
                    country = country,
                    city = city,
                    street = street,
                    houseNumber = houseNumber,
                    postCode = postCode,
                    discount = discount,
                    totalAmount = chosenClient.value!!.totalAmount,
                    specialNotes = specialNotes,
                )
            )
        }
        chosenClient.value = null
    }
    showClientList.value = !showClientList.value
}
