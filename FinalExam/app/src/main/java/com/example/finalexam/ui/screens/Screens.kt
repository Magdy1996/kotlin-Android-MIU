package com.example.finalexam.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalexam.data.Item
import com.example.finalexam.viewmodel.AuthViewModel
import com.example.finalexam.viewmodel.HomeViewModel
import com.example.finalexam.viewmodel.ItemViewModel

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    val authViewModel: AuthViewModel = viewModel()
    var username by remember { mutableStateOf("magdy") }
    var password by remember { mutableStateOf("123456") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login", modifier = Modifier.padding(bottom = 24.dp))
        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            authViewModel.login(username)
            onLogin()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Login")
        }
    }
}

@Composable
fun HomeScreen(onOpenItems: () -> Unit) {
    val homeViewModel: HomeViewModel = viewModel()
    val lastJoke by homeViewModel.lastJoke.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        Text(text = "Today's Joke", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = lastJoke ?: "No joke yet. Will fetch in background.")

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Item Categories", modifier = Modifier.padding(vertical = 8.dp))
        val categories = listOf("ELECTRONICS", "CLOTHING", "BOOKS")
        LazyColumn {
            items(categories) { cat ->
                Surface(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenItems() }
                    .padding(8.dp)) {
                    Text(text = cat, modifier = Modifier.padding(12.dp))
                }
            }
        }
    }
}

@Composable
fun ItemListScreen(onItemSelected: (Long) -> Unit) {
    val itemViewModel: ItemViewModel = viewModel()
    val items by itemViewModel.items.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(12.dp)) {
            items(items) { item ->
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f).clickable { onItemSelected(item.id) }) {
                        Text(text = item.name)
                        Text(text = "${'$'}${item.price} x ${item.quantity} = ${'$'}${item.price * item.quantity}")
                    }
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", modifier = Modifier.clickable {
                        // quick edit: increment price by 50 for demo
                        itemViewModel.update(item.copy(price = item.price + 50.0))
                    })
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", modifier = Modifier.clickable {
                        itemViewModel.delete(item)
                    })
                }
            }
        }

        FloatingActionButton(onClick = { showDialog = true }, modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {
            Text(text = "+")
        }

        if (showDialog) {
            var name by remember { mutableStateOf("") }
            var category by remember { mutableStateOf("ELECTRONICS") }
            var price by remember { mutableStateOf(0.0) }
            var quantity by remember { mutableStateOf(1) }

            AlertDialog(onDismissRequest = { showDialog = false }, title = { Text("Add Item") }, text = {
                Column {
                    OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(value = if (price == 0.0) "" else price.toString(), onValueChange = { price = it.toDoubleOrNull() ?: 0.0 }, label = { Text("Price") })
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(value = quantity.toString(), onValueChange = { quantity = it.toIntOrNull() ?: 1 }, label = { Text("Quantity") })
                }
            }, confirmButton = {
                Button(onClick = {
                    val it = Item(name = name, category = category, price = price, quantity = quantity)
                    itemViewModel.insert(it)
                    showDialog = false
                }) { Text("Confirm") }
            })
        }
    }
}

@Composable
fun ItemDetailScreen(itemId: Long) {
    val itemViewModel: ItemViewModel = viewModel()
    LaunchedEffect(itemId) { itemViewModel.loadItem(itemId) }
    val item by itemViewModel.selectedItem.collectAsState()

    var showEdit by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        if (item == null) {
            Text("Item not found")
        } else {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item!!.name)
                    Text(text = "${'$'}${item!!.price}")
                    val displayCategory = item!!.category.lowercase().replaceFirstChar { it.uppercase() }
                    Text(text = item!!.description ?: "This is a product in $displayCategory" )
                }
                IconButton(onClick = { showEdit = true }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = { itemViewModel.delete(item!!) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }

            if (showEdit) {
                var name by remember { mutableStateOf(item!!.name) }
                var category by remember { mutableStateOf(item!!.category) }
                var price by remember { mutableStateOf(item!!.price) }
                var quantity by remember { mutableStateOf(item!!.quantity) }

                AlertDialog(onDismissRequest = { showEdit = false }, title = { Text("Edit Item") }, text = {
                    Column {
                        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(value = price.toString(), onValueChange = { price = it.toDoubleOrNull() ?: price }, label = { Text("Price") })
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(value = quantity.toString(), onValueChange = { quantity = it.toIntOrNull() ?: quantity }, label = { Text("Quantity") })
                    }
                }, confirmButton = {
                    Button(onClick = {
                        val updated = item!!.copy(name = name, category = category, price = price, quantity = quantity)
                        itemViewModel.update(updated)
                        showEdit = false
                    }) { Text("Update") }
                })
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    val authViewModel: AuthViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        Button(onClick = { authViewModel.logout() }) {
            Text("Logout")
        }
    }
}
