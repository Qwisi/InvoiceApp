package com.example.invoiceapp.view.product

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.invoiceapp.model.ApplicationDB
import com.example.invoiceapp.model.entities.category.Category
import com.example.invoiceapp.model.entities.measurement.Measurement
import com.example.invoiceapp.model.entities.product.Product
import com.example.invoiceapp.model.entities.product.ProductWithCM
import com.example.invoiceapp.model.repository.CategoryRepository
import com.example.invoiceapp.model.repository.ProductRepository
import com.example.invoiceapp.view.composes.SimpleTopBar
import com.example.invoiceapp.view.composes.StyledComponentOverlay
import com.example.invoiceapp.view.composes.StyledNavigationBar
import com.example.invoiceapp.viewModel.CategoryViewModel
import com.example.invoiceapp.viewModel.ProductViewModel

@Preview
@Composable
fun ProductViewPreview() {
    val tempDb = ApplicationDB.getPreviewDatabase(LocalContext.current)

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
        price = 23.5,
        measurementValue = 1.0,
        idCategory = 1,
        idMeasurement = 1
    )
    val candy = Product(
        title = "Candy",
        description = "sooo sweet",
        price = 12.3,
        measurementValue = 100.0,
        idCategory = 2,
        idMeasurement = 3
    )

    val navController = rememberNavController()
    // Insert sample data
    LaunchedEffect(Unit) {
        categoryViewModel.insertCategory(categoryVeg)
        categoryViewModel.insertCategory(categorySw)

        productViewModel.insert(measurementKg)
        productViewModel.insert(measurementCm)
        productViewModel.insert(measurementGr)

        productViewModel.insert(apple)
        productViewModel.insert(candy)
    }

    ProductView(
        navController,
        productViewModel,
        categoryViewModel
    )
}

@Composable
fun ProductView(
    navController: NavController,
    productViewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel
){
    var currentOverlay by remember { mutableStateOf(OverlayType.NONE) }
    var productToDelete by remember { mutableStateOf<ProductWithCM?>(null) }

    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Product"
            ) { /* TODO: Implement on back arrow click */ }
        },
        bottomBar = {
            StyledNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        ProductBody(
            innerPadding = innerPadding,
            productViewModel = productViewModel,
            categoryViewModel = categoryViewModel,
            onCategoryAdd = { currentOverlay = OverlayType.ADD_CATEGORY },
            onMeasurementAdd = { currentOverlay = OverlayType.ADD_MEASUREMENT },
            onProductLongPress = {
                currentOverlay = OverlayType.CONFIRMATION
                productToDelete = it
            }
        )
    }

    // Display overlay based on currentOverlay type
    when (currentOverlay) {
        OverlayType.ADD_CATEGORY -> {
            StyledComponentOverlay {
                AddCategoryOverlay(categoryViewModel) { currentOverlay = OverlayType.NONE }
            }
        }
        OverlayType.ADD_MEASUREMENT -> {
            StyledComponentOverlay {
                AddMeasurementOverlay(productViewModel) { currentOverlay = OverlayType.NONE }
            }
        }
        OverlayType.CONFIRMATION ->{
            StyledComponentOverlay {
                productToDelete?.let {
                    ConfirmProductDeleteOverlay(it, productViewModel) {currentOverlay = OverlayType.NONE}
                }
            }
        }
        else -> { /* No overlay */ }
    }

}
private enum class OverlayType {
    NONE, ADD_CATEGORY, ADD_MEASUREMENT, CONFIRMATION
}