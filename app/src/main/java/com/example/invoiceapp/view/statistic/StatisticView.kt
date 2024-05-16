package com.example.invoiceapp.view.statistic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.invoiceapp.model.ApplicationDB
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.model.entities.client.Client
import com.example.invoiceapp.model.entities.invoice.Invoice
import com.example.invoiceapp.model.entities.invoice.InvoiceCrossItems
import com.example.invoiceapp.model.entities.invoiceItem.InvoiceItem
import com.example.invoiceapp.model.entities.measurement.Measurement
import com.example.invoiceapp.model.entities.product.Product
import com.example.invoiceapp.model.repository.CategoryRepository
import com.example.invoiceapp.model.repository.ClientRepository
import com.example.invoiceapp.model.repository.InvoiceRepository
import com.example.invoiceapp.model.repository.ProductRepository
import com.example.invoiceapp.ui.theme.HeadLineRobotoMedium
import com.example.invoiceapp.view.composes.SimpleTopBar
import com.example.invoiceapp.view.composes.StyledNavigationBar
import com.example.invoiceapp.view.composes.StyledStatisticItem
import com.example.invoiceapp.viewModel.CategoryViewModel
import com.example.invoiceapp.viewModel.ClientViewModel
import com.example.invoiceapp.viewModel.InvoiceViewModel
import com.example.invoiceapp.viewModel.ProductViewModel
import java.util.Date

@Preview
@Composable
fun StatisticViewPreview(){
    val tempDb = ApplicationDB.getPreviewDatabase(LocalContext.current)

    val clientDao = tempDb.clientDao()
    val clientRepository = ClientRepository(clientDao)
    val clientViewModel = ClientViewModel(clientRepository)

    // Sample data
    val johnDoe = Client(
        name = "John",
        surname = "Doe",
        phoneNumber = "1234567890",
        discount = 0.10,
        totalAmount = 120.00,
        specialNotes = "Preferred customer",
        country = "Sample Country",
        city = "Sample City",
        street = "Sample Street",
        houseNumber = 42,
        postCode = "12345"
    )

    val doeJohn = Client(
        name = "Doe",
        surname = "John",
        phoneNumber = "9876543210",
        discount = 0.0,
        totalAmount = 0.0,
        specialNotes = "",
        country = "Sample Country",
        city = "Sample City",
        street = "Sample Street",
        houseNumber = 23,
        postCode = "54321"
    )

    //Measurement attributes
    val measurementDao = tempDb.measurementDao()
    val measurementKg = Measurement(id = 1, unit = "kg")
    val measurementCm = Measurement(id = 2, unit = "cm")
    val measurementGr = Measurement(id = 3, unit = "gr")

    //Category attributes
    val categoryDao = tempDb.categoryDao()
    val categoryRepository = CategoryRepository(categoryDao)
    val categoryViewModel = CategoryViewModel(categoryRepository)
    val categoryVeg = Category(id = 1, name = "Vegetables")
    val categorySw = Category(id = 2, name = "Sweets")

    //Product attributes
    val productDao = tempDb.productDao()
    val productRepository = ProductRepository(productDao, measurementDao)
    val productViewModel = ProductViewModel(productRepository)

    val apple = Product(
        title = "Apple",
        description = "sweet red apple",
        price = 24.0,
        measurementValue = 1.0,
        idCategory = 1,
        idMeasurement = 1
    )
    val candy = Product(
        title = "Candy",
        description = "so sweet",
        price = 12.3,
        measurementValue = 100.0,
        idCategory = 2,
        idMeasurement = 3
    )

    //Invoice attributes
    val invoiceItemDao = tempDb.invoiceItemDao()
    val invoiceCrossItemsDao = tempDb.invoiceCrossItemsDao()

    val invoiceDao = tempDb.invoiceDao()
    val invoiceRepository = InvoiceRepository(invoiceDao, invoiceItemDao, invoiceCrossItemsDao)
    val invoiceViewModel = InvoiceViewModel(invoiceRepository)

    val itemCandy = InvoiceItem(title = "Candy", quantity = 5, price = 23.5, idProduct = 1)
    val itemApple = InvoiceItem(title = "Apple", quantity = 10, price = 12.3, idProduct = 2)

    val invoice = Invoice(
        id = 1,
        number = "0014-04-2024",
        discount = 0.0,
        totalPrice = 240.5,
        dateCreated = Date(),
        idClient = 1
    )

    val invoiceWithItemCandy = InvoiceCrossItems( invoiceId = 1,  invoiceItemId = 1 )
    val invoiceWithItemApple = InvoiceCrossItems( invoiceId = 1, invoiceItemId = 2)

    val navController = rememberNavController()
    // Insert sample data
    LaunchedEffect(Unit) {
        clientViewModel.insert(johnDoe)
        clientViewModel.insert(doeJohn)

        categoryViewModel.insertCategory(categoryVeg)
        categoryViewModel.insertCategory(categorySw)

        productViewModel.insert(measurementKg)
        productViewModel.insert(measurementCm)
        productViewModel.insert(measurementGr)

        productViewModel.insert(apple)
        productViewModel.insert(candy)

        invoiceViewModel.insertInvoiceItem(itemCandy)
        invoiceViewModel.insertInvoiceItem(itemApple)

        invoiceViewModel.insertInvoice(invoice)

        invoiceViewModel.insertInvoiceCrossItems(invoiceWithItemCandy)
        invoiceViewModel.insertInvoiceCrossItems(invoiceWithItemApple)
    }

    StatisticView(
        navController = navController,
        invoiceViewModel = invoiceViewModel,
        productViewModel = productViewModel,
        clientViewModel = clientViewModel
    )
}
@Composable
fun StatisticView(
    navController: NavController,
    invoiceViewModel: InvoiceViewModel,
    productViewModel: ProductViewModel,
    clientViewModel: ClientViewModel
){
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Statistic"
            ) {
                // on back arrow click
                //navController.popBackStack()
            }
        },
        bottomBar = {
            StyledNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        StatisticBody(
            innerPadding = innerPadding,
            invoiceViewModel = invoiceViewModel,
            productViewModel = productViewModel,
            clientViewModel = clientViewModel
        )
    }
}

@Composable
fun StatisticBody(
    innerPadding: PaddingValues,
    invoiceViewModel: InvoiceViewModel,
    productViewModel: ProductViewModel,
    clientViewModel: ClientViewModel
){
    val searchValue = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //SimpleSearchBar(textValue = searchValue)
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ){
            InvoiceStatistic(invoiceViewModel)
            ProductStatistic(productViewModel)
            ClientStatistic(clientViewModel)
        }

    }
}

@Composable
fun InvoiceStatistic(
    vm: InvoiceViewModel
){
    Text(modifier = Modifier.padding(vertical = 5.dp), text = "Invoice statistic", style = HeadLineRobotoMedium)
    StyledStatisticItem(headLine = "Total invoices", data = "${vm.getSize()}")
    StyledStatisticItem(headLine = "Average price", data = "${vm.getAveragePrice()}")
    StyledStatisticItem(headLine = "Today invoices", data = "${vm.getTodaySize()}")
    StyledStatisticItem(headLine = "Today average price", data = "${vm.getTodayAveragePrice()}")
}

@Composable
fun ProductStatistic(
    vm: ProductViewModel
){
    Text(modifier = Modifier.padding(vertical = 5.dp),text = "Product statistic", style = HeadLineRobotoMedium)
    StyledStatisticItem(headLine = "Total products", data = "${vm.getSize()}")
    StyledStatisticItem(headLine = "Average price", data = "${vm.getAveragePrice()}")
}

@Composable
fun ClientStatistic(
    vm: ClientViewModel
){
    Text(modifier = Modifier.padding(vertical = 5.dp), text = "Client statistic", style = HeadLineRobotoMedium)
    StyledStatisticItem(headLine = "Total clients", data = "${vm.getSize()}")
    StyledStatisticItem(headLine = "Average total amount", data = "${vm.getAverageTotalAmount()}")
}