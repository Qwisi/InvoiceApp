package com.example.invoiceapp.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.invoiceapp.R
import com.example.invoiceapp.viewModel.ClientViewModel

enum class Screen(val route: String, val activeIcon: Int, val inactiveIcon: Int) {
    Invoice("invoice", R.drawable.icon_invoice_active, R.drawable.icon_invoice_inactive),
    Statistic("statistic", R.drawable.icon_statistic_active, R.drawable.icon_statistic_inactive),
    Product("product", R.drawable.icon_product_active, R.drawable.icon_product_inactive),
    Client("client", R.drawable.icon_client_active, R.drawable.icon_client_inactive);
}

@Composable
fun MainNavigationController() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Client.route) {
        Screen.entries.forEach { screen ->
            composable(screen.route) {
                when (screen) {
                    Screen.Invoice -> InvoiceScreen(navController)
                    //Screen.Statistic -> StatisticScreen()
                    //Screen.Product -> ProductScreen()
                    Screen.Client -> ClientScreen(navController)
                    else -> {}
                }
            }
        }
    }
}
