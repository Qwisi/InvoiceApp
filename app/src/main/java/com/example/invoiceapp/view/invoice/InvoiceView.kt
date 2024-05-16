package com.example.invoiceapp.view.invoice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.invoiceapp.model.ApplicationDB
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.model.entities.client.Client
import com.example.invoiceapp.model.entities.invoice.Invoice
import com.example.invoiceapp.model.entities.invoice.InvoiceCrossItems
import com.example.invoiceapp.model.entities.invoice.InvoiceWithIC
import com.example.invoiceapp.model.entities.invoiceItem.InvoiceItem
import com.example.invoiceapp.model.entities.measurement.Measurement
import com.example.invoiceapp.model.entities.product.Product
import com.example.invoiceapp.model.repository.CategoryRepository
import com.example.invoiceapp.model.repository.ClientRepository
import com.example.invoiceapp.model.repository.InvoiceRepository
import com.example.invoiceapp.model.repository.ProductRepository
import com.example.invoiceapp.view.composes.ProductItem
import com.example.invoiceapp.view.composes.StyledListItem
import com.example.invoiceapp.view.composes.StyledSearchBar
import com.example.invoiceapp.view.composes.SimpleTopBar
import com.example.invoiceapp.view.composes.StyledButton
import com.example.invoiceapp.view.composes.StyledClientInvoiceItem
import com.example.invoiceapp.view.composes.StyledComponentOverlay
import com.example.invoiceapp.view.composes.StyledConfirmationCard
import com.example.invoiceapp.view.composes.StyledNavigationBar
import com.example.invoiceapp.view.composes.StyledOutlinedTextField
import com.example.invoiceapp.view.composes.StyledOutlinedTextFieldProps
import com.example.invoiceapp.viewModel.CategoryViewModel
import com.example.invoiceapp.viewModel.ClientViewModel
import com.example.invoiceapp.viewModel.InvoiceViewModel
import com.example.invoiceapp.viewModel.ProductViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

@Preview
@Composable
fun InvoiceViewPreview(){
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

    InvoiceView(
        navController = navController,
        invoiceViewModel = invoiceViewModel,
        productViewModel = productViewModel,
        clientViewModel = clientViewModel
    )
}

@Composable
fun InvoiceView(
    navController: NavController,
    invoiceViewModel: InvoiceViewModel,
    productViewModel: ProductViewModel,
    clientViewModel: ClientViewModel
){

    val totalPrice = remember { mutableDoubleStateOf(0.0) }
    var invoiceToDelete by remember { mutableStateOf<InvoiceWithIC?>(null) }

    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Invoice",
                price = totalPrice.doubleValue
            ){
                navController.popBackStack()
            }
        },
        bottomBar = {
            StyledNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        InvoiceBody(
            innerPadding = innerPadding,
            invoiceViewModel = invoiceViewModel,
            productViewModel = productViewModel,
            clientViewModel = clientViewModel,
            totalPrice = totalPrice,
            onInvoiceLongPress = {invoiceToDelete = it}
        )
    }

    if(invoiceToDelete != null){
        StyledComponentOverlay {
            StyledConfirmationCard(
                modifier = Modifier.padding(horizontal = 20.dp),
                info = "${invoiceToDelete!!.invoice.number} made for ${invoiceToDelete!!.client.surname}",
                onConfirm = {
                    invoiceViewModel.deleteInvoice(invoiceToDelete!!)
                    invoiceToDelete = null
                }
            ) {
                invoiceToDelete = null
            }
        }
    }
}

enum class BodyScreen {
    LIST, ADD_PRODUCT, ADD_CLIENT
}

@Composable
fun InvoiceBody(
    innerPadding: PaddingValues,
    invoiceViewModel: InvoiceViewModel,
    productViewModel: ProductViewModel,
    clientViewModel: ClientViewModel,
    totalPrice: MutableState<Double>,
    onInvoiceLongPress: (InvoiceWithIC) -> Unit
){
    val searchValue = remember { mutableStateOf("")  }
    var currentBody by remember { mutableStateOf(BodyScreen.LIST) }

    val invoiceList = invoiceViewModel.invoices.asFlow().collectAsState(initial = emptyList()).value
    val productList = productViewModel.liveData.asFlow().collectAsState(initial = emptyList()).value
    val clientList = clientViewModel.clients.asFlow().collectAsState(initial = emptyList()).value

    val listOfChosenProducts = remember { mutableListOf<Pair<Product, Int>>() }
    val chosenClient = remember { mutableStateOf<Client?>(null) }

    val discount = remember (chosenClient.value) { mutableStateOf("${(chosenClient.value?.discount ?: 0.0) * 100}")}

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StyledSearchBar(
            textValue = searchValue,
        )
        when(currentBody) {
            BodyScreen.LIST ->{
                InvoiceList(
                    modifier = Modifier.weight(1f),
                    searchValue = searchValue,
                    invoiceList = invoiceList,
                    onInvoiceLongPress = { onInvoiceLongPress(it) }
                )
            }
            BodyScreen.ADD_PRODUCT -> {
                InvoiceAddProduct(
                    modifier = Modifier.weight(1f),
                    searchValue = searchValue,
                    productList = productList,
                    chosenProducts = listOfChosenProducts,
                    totalPrice = totalPrice
                )
            }
            BodyScreen.ADD_CLIENT -> {
                InvoiceAddClient(
                    modifier = Modifier.weight(1f),
                    searchValue = searchValue,
                    clientList = clientList,
                    chosenClient = chosenClient,
                )
                if(chosenClient.value != null) {
                    StyledOutlinedTextField(
                        props = StyledOutlinedTextFieldProps(
                            textFieldValue = discount,
                            labelText = "Discount %",
                        )
                    )
                }
            }
        }
        StyledButton(
            modifier = Modifier.padding(vertical = 10.dp),
            text = when(currentBody){
                BodyScreen.LIST -> "+ Create Invoice"
                BodyScreen.ADD_PRODUCT -> "Next"
                BodyScreen.ADD_CLIENT -> "Create"
            }
        ){
            when(currentBody){
                BodyScreen.LIST -> currentBody = BodyScreen.ADD_PRODUCT
                BodyScreen.ADD_PRODUCT -> currentBody = BodyScreen.ADD_CLIENT
                BodyScreen.ADD_CLIENT -> {
                    // Creating list of items for invoice and counting total price
                    val invoiceItems = mutableListOf<InvoiceItem>()
                    listOfChosenProducts.forEach {(product, quantity) ->
                        invoiceItems.add(
                            InvoiceItem(
                            title = product.title,
                            quantity = quantity,
                            price = product.price,
                            idProduct = product.id
                        )
                        )
                        totalPrice.value += product.price * quantity
                    }

                    //Inserting invoice
                    val newInvoice = Invoice(
                        number = getNewNumber(invoiceList.last().invoice.number),
                        discount = discount.value.toDouble() / 100,
                        totalPrice = totalPrice.value,
                        dateCreated = Date(),
                        idClient = chosenClient.value!!.id
                    )
                    invoiceViewModel.insertInvoice(newInvoice)

                    // inserting each item to db and connecting item id to invoice id
                    invoiceItems.forEach {
                        invoiceViewModel.insertInvoiceItem(it)
                        invoiceViewModel.insertInvoiceCrossItems(
                            InvoiceCrossItems(
                            invoiceId = newInvoice.id,
                            invoiceItemId = it.id
                        )
                        )
                    }
                    // notification "are u sure"

                    // creating pdf file

                    currentBody = BodyScreen.LIST
                }
            }
        }
    }
}

@Composable
fun InvoiceList(
    modifier: Modifier,
    searchValue: MutableState<String>,
    invoiceList: List<InvoiceWithIC>,
    onInvoiceLongPress: (InvoiceWithIC) -> Unit
){
    LazyColumn(modifier = modifier.padding(vertical = 10.dp)) {
        val filteredInvoices = if (searchValue.value.isNotEmpty()) {
            invoiceList.filter {
                it.client.name.contains(searchValue.value, ignoreCase = true) ||
                it.client.surname.contains(searchValue.value, ignoreCase = true)
            }
        } else {
            invoiceList
        }

        items(filteredInvoices) {
            StyledListItem(
                modifier = Modifier,
                overLineText = "Status",
                headLine = it.invoice.number,
                supportingText = "${it.client.name} ${it.client.surname}",
                onClick = {
                    // open pdf with invoice
                },
                onLongPress = {
                    onInvoiceLongPress(it)
                }
            )
        }
    }
}

@Composable
fun InvoiceAddProduct(
    modifier: Modifier,
    searchValue: MutableState<String>,
    productList: List<Product>,
    chosenProducts: MutableList<Pair<Product,Int>>,
    totalPrice: MutableState<Double>
){
    LazyColumn(modifier = modifier.padding(vertical = 10.dp)) {
        val filteredProducts = if (searchValue.value.isNotEmpty()) {
            productList.filter {
                it.title.contains(searchValue.value, ignoreCase = true)
            }
        } else {
            productList
        }

        items(filteredProducts) { product ->
            val isChosen = remember { mutableStateOf(false) }
            val quantity = remember { mutableIntStateOf(0) }

            ProductItem(
                modifier = Modifier,
                productName = product.title,
                isChosen = isChosen,
                quantity = quantity
            )

            // Observe changes in selection and quantity
            LaunchedEffect(isChosen.value, quantity.intValue) {
                if (isChosen.value) {
                    val existingProduct = chosenProducts.find { it.first == product }
                    if (existingProduct != null) {
                        chosenProducts[chosenProducts.indexOf(existingProduct)] = existingProduct.first to quantity.intValue
                        totalPrice.value = chosenProducts.sumOf { it.first.price * it.second }
                    } else {
                        chosenProducts.add(product to quantity.intValue)
                        totalPrice.value = chosenProducts.sumOf { it.first.price * it.second }
                    }
                }
            }
        }
    }
}

@Composable
fun InvoiceAddClient(
    modifier: Modifier,
    searchValue: MutableState<String>,
    clientList: List<Client>,
    chosenClient: MutableState<Client?>,
){
    LazyColumn(modifier = modifier.padding(vertical = 10.dp)) {
        val filteredClients = if (searchValue.value.isNotEmpty()) {
            clientList.filter {
                it.name.contains(searchValue.value, ignoreCase = true) ||
                it.surname.contains(searchValue.value, ignoreCase = true)
            }
        } else {
            clientList
        }

        items(filteredClients) {
            val isChosen = remember { mutableStateOf(chosenClient.value == it) }

            StyledClientInvoiceItem(
                modifier = Modifier,
                clientName = "${it.name} ${it.surname}",
                isChosen = isChosen
            )

            // Respond to changes in the check state
            LaunchedEffect(isChosen.value) {
                if (isChosen.value) {
                    if (chosenClient.value != it) {
                        chosenClient.value = it
                    }
                } else {
                    if (chosenClient.value == it) {
                        chosenClient.value = null
                    }
                }
            }
        }
    }
}

fun getNewNumber(lastNumber: String): String{
    val parts = lastNumber.split("-")
    val sequence = parts[0].toInt()
    val lastMonth = parts[1].toInt()
    val lastYear = parts[2].toInt()

    val currentDate = LocalDate.now()
    val currentMonth = currentDate.monthValue
    val currentYear = currentDate.year

    val nextSequenceNumber = if (lastMonth == currentMonth && lastYear == currentYear) {
        sequence + 1
    } else {
        1  // Reset to 1 if it's a new month or year
    }

    val formattedSequence = "%04d".format(nextSequenceNumber)  // Format with leading zeros
    return "$formattedSequence-${currentDate.format(DateTimeFormatter.ofPattern("MM-yyyy"))}"
}