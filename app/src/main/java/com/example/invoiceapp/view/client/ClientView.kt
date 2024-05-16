package com.example.invoiceapp.view.client

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.invoiceapp.model.ApplicationDB
import com.example.invoiceapp.model.entities.client.Client
import com.example.invoiceapp.model.repository.ClientRepository
import com.example.invoiceapp.view.composes.SimpleTopBar
import com.example.invoiceapp.view.composes.StyledComponentOverlay
import com.example.invoiceapp.view.composes.StyledConfirmationCard
import com.example.invoiceapp.view.composes.StyledNavigationBar
import com.example.invoiceapp.viewModel.ClientViewModel

@Preview
@Composable
fun ClientViewPreview() {
    val tempDb = ApplicationDB.getPreviewDatabase(LocalContext.current)
    val clientDao = tempDb.clientDao()
    val clientRepository = ClientRepository(clientDao)
    val viewModel = ClientViewModel(clientRepository)

    // Sample data
    val sampleClient = Client(
        name = "John",
        surname = "Doe",
        phoneNumber = "1234567890",
        discount = 0.15,
        totalAmount = 120.00,
        specialNotes = "Preferred customer",
        country = "Sample Country",
        city = "Sample City",
        street = "Sample Street",
        houseNumber = 42,
        postCode = "12345"
    )

    // Insert sample data
    LaunchedEffect(Unit) {
        viewModel.insert(sampleClient)
    }

    val navController = rememberNavController()
    ClientView(navController, viewModel)
}

@Composable
fun ClientView(
    navController: NavController,
    viewModel: ClientViewModel
){
    var clientToDelete by remember { mutableStateOf<Client?>(null) }

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
        ClientBody(
            innerPadding = innerPadding,
            viewModel = viewModel,
            onClientLongPress = { clientToDelete = it }
        )
    }

    // Confirmation of client deleting if client line was pressed
    if(clientToDelete != null){
        StyledComponentOverlay {
            StyledConfirmationCard(
                modifier = Modifier.padding(horizontal = 20.dp),
                action = "delete",
                page = "client",
                info = "${clientToDelete!!.name} ${clientToDelete!!.surname} phone number ${clientToDelete!!.phoneNumber}",
                onConfirm = {
                    viewModel.delete(clientToDelete!!)
                    clientToDelete = null
                },
                onDismiss = { clientToDelete = null }
            )
        }
    }
}