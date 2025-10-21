package com.example.finalexam.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.finalexam.ui.screens.HomeScreen
import com.example.finalexam.ui.screens.ItemDetailScreen
import com.example.finalexam.ui.screens.ItemListScreen
import com.example.finalexam.ui.screens.LoginScreen
import com.example.finalexam.ui.screens.SettingsScreen
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.finalexam.viewmodel.AuthViewModel

sealed class Screen(val route: String, val title: String) {
    object Login : Screen("login", "Login")
    object Home : Screen("home", "Home")
    object ItemList : Screen("items", "Items")
    object ItemDetail : Screen("detail", "Detail")
    object Settings : Screen("settings", "Settings")
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val auth: AuthViewModel = viewModel()
    val isLoggedIn by auth.isLoggedIn.collectAsState()

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // When login state changes, navigate accordingly
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            // go to home and clear login from backstack
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        } else {
            // go to login and clear rest
            navController.navigate(Screen.Login.route) {
                popUpTo(0)
            }
        }
    }

    val showBottomBar = currentRoute != Screen.Login.route

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) Screen.Home.route else Screen.Login.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(onLogin = {
                    // actual navigation handled by isLoggedIn observer
                })
            }
            composable(Screen.Home.route) {
                HomeScreen(onOpenItems = { category ->
                    navController.navigate("${Screen.ItemList.route}?category=$category")
                })
            }
            composable("${Screen.ItemList.route}?category={category}") { backStack ->
                val category = backStack.arguments?.getString("category")
                ItemListScreen(
                    onItemSelected = { id -> navController.navigate("${Screen.ItemDetail.route}/$id") },
                    category = category
                )
            }
            composable("${Screen.ItemDetail.route}/{itemId}") { backStack ->
                val idStr = backStack.arguments?.getString("itemId")
                val id = idStr?.toLongOrNull() ?: 0L
                ItemDetailScreen(itemId = id)
            }
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(Screen.Home, Screen.Settings)
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(selected = currentRoute == screen.route,
                onClick = { navController.navigate(screen.route) },
                icon = {
                    when (screen) {
                        Screen.Home -> Icon(Icons.Default.Home, contentDescription = "Home")
                        Screen.Settings -> Icon(Icons.Default.Settings, contentDescription = "Settings")
                        else -> Icon(Icons.Default.Home, contentDescription = screen.title)
                    }
                },
                label = { Text(screen.title) }
            )
        }
    }
}
