package com.dgomesdev.mycv.ui.features

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CVApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { CVBottomBar(
            onNavigate = { navController.navigate(it) },
            screen = "Profile") }
    ) {
        CVNavHost(
            navController = navController,
            modifier = Modifier.padding(it)
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val context = LocalContext.current
    CenterAlignedTopAppBar(
        title = { Text("My CV") },
        actions = {
            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "Developed by Dgomes Dev",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Developed by Dgomes Dev"
                )
            }
        }
    )
}

@Composable
fun CVBottomBar(
    onNavigate: (String) -> Unit,
    screen: String
) {
    var currentScreen by rememberSaveable {
        mutableStateOf(0)
    }
    val tabs = listOf(screen, "Contact")
    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = currentScreen == index,
                onClick = { onNavigate(tab) ; currentScreen = index },
                icon = {
                    if (index == 0) Icon(imageVector = Icons.Default.List, contentDescription = tab)
                    else Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Contact")
                },
                label = {
                    Text(
                        if (index == 0) tab
                        else "Contact"
                    )
                }
            )
        }
    }
}