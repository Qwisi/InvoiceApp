package com.example.invoiceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.invoiceapp.model.ApplicationDB
import com.example.invoiceapp.repository.ClientRepository
import com.example.invoiceapp.ui.theme.InvoiceAppTheme
import com.example.invoiceapp.view.MainNavigationController
import com.example.invoiceapp.viewModel.ClientViewModel
import com.example.invoiceapp.viewModel.factories.ClientViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create an instance of the ApplicationDB
        val appDb = ApplicationDB.getDatabase(this)

        // Create an instance of the Customer Factory
        val repositoryCustomer = ClientRepository(appDb.clientDao())
        val factoryCustomer = ClientViewModelFactory(repositoryCustomer)

        setContent {
            InvoiceAppTheme {
                // Use the ViewModel factory here
                val viewModelClient: ClientViewModel = viewModel(factory = factoryCustomer)
                MainNavigationController()
            }
        }
    }
}
