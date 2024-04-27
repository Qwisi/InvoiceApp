package com.example.invoiceapp.view.client

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.invoiceapp.model.client.Client
import com.example.invoiceapp.view.composes.StyledClientItem

@Composable
fun ClientList(
    modifier: Modifier = Modifier,
    searchValue: MutableState<String>,
    clientList: List<Client>,
    showClientList: MutableState<Boolean>,
    onClientChosen: (Client) -> Unit,
    onClientLongPress: (Client) -> Unit
){
    LazyColumn(modifier = modifier.padding(vertical = 10.dp)) {
        val filteredClients = if (searchValue.value.isNotEmpty()) {
            clientList.filter { client ->
                client.name.contains(searchValue.value, ignoreCase = true) ||
                        client.surname.contains(searchValue.value, ignoreCase = true)
            }
        } else {
            clientList
        }

        items(filteredClients) { client ->
            StyledClientItem(
                clientName = "${client.name} ${client.surname}",
                onClick = {
                    showClientList.value = false
                    onClientChosen(client)
                },
                onLongPress = { onClientLongPress(client) }
            )
        }
    }
}