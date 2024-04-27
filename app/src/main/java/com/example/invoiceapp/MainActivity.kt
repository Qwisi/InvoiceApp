package com.example.invoiceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.invoiceapp.model.ApplicationDB
import com.example.invoiceapp.repository.CategoryRepository
import com.example.invoiceapp.repository.ClientRepository
import com.example.invoiceapp.repository.InvoiceRepository
import com.example.invoiceapp.repository.ProductRepository
import com.example.invoiceapp.ui.theme.InvoiceAppTheme
import com.example.invoiceapp.view.MainNavigationController
import com.example.invoiceapp.viewModel.CategoryViewModel
import com.example.invoiceapp.viewModel.ClientViewModel
import com.example.invoiceapp.viewModel.InvoiceViewModel
import com.example.invoiceapp.viewModel.ProductViewModel
import com.example.invoiceapp.viewModel.factories.CategoryViewModelFactory
import com.example.invoiceapp.viewModel.factories.ClientViewModelFactory
import com.example.invoiceapp.viewModel.factories.InvoiceViewModelFactory
import com.example.invoiceapp.viewModel.factories.ProductViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create an instance of the ApplicationDB
        val appDb = ApplicationDB.getDatabase(this)

        // Create an instance of the Category Factory
        val repositoryCategory = CategoryRepository(appDb.categoryDao())
        val factoryCategory = CategoryViewModelFactory(repositoryCategory)

        // Create an instance of the Customer Factory
        val repositoryCustomer = ClientRepository(appDb.clientDao())
        val factoryCustomer = ClientViewModelFactory(repositoryCustomer)

        // Create an instance of the Product Factory
        val repositoryProduct = ProductRepository(appDb.productDao(), appDb.measurementDao())
        val factoryProduct = ProductViewModelFactory(repositoryProduct)

        // Create an instance of the Invoice Factory
        val repositoryInvoice = InvoiceRepository(appDb.invoiceDao(), appDb.invoiceItemDao(), appDb.invoiceCrossItemsDao())
        val factoryInvoice = InvoiceViewModelFactory(repositoryInvoice)

        setContent {
            InvoiceAppTheme {
                // Using ViewModel factory here
                val clientViewModel: ClientViewModel = viewModel(factory = factoryCustomer)
                val categoryViewModel: CategoryViewModel = viewModel(factory = factoryCategory)
                val productViewModel: ProductViewModel = viewModel(factory = factoryProduct)
                val invoiceViewModel: InvoiceViewModel = viewModel(factory = factoryInvoice)

                MainNavigationController(
                    categoryViewModel,
                    clientViewModel,
                    productViewModel,
                    invoiceViewModel
                )
            }
        }
    }
}
