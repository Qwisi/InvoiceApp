package com.example.invoiceapp.view.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.invoiceapp.view.Screen

@Preview(showBackground = true)
@Composable
fun NavigationBarPreview() {
    val navController = rememberNavController()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        StyledNavigationBar(
            navController = navController
        )
    }
}

@Composable
fun StyledNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Screen.entries.forEach { screen ->
            val icon = if (screen.route == currentRoute) screen.activeIcon else screen.inactiveIcon
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "${screen.route} menu icon",
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    }
            )
        }
    }
}