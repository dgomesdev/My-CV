package com.dgomesdev.mycv.ui.features

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dgomesdev.mycv.R
import kotlinx.coroutines.launch


@Composable
fun CVApp() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { CVTopBar() },
        drawerContent = {
            Image(
                painter = painterResource(R.drawable.dgomesdev_logo),
                contentDescription = "DGomes Dev logo"
            )
            Sections(
                onNavigate = { navController.navigate(it) },
                onExpandChange = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        },
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

@Composable
fun CVTopBar() {
    val context = LocalContext.current
    TopAppBar(
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
fun Sections(
    onNavigate: (String) -> Unit,
    onExpandChange: () -> Unit
) {
    Column {
        val sections = listOf(
            "Profile",
            "Work experience",
            "Education",
            "Languages",
            "Other",
            "Contact"
        )
        for (section in sections) {
            Text(
                section,
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        onNavigate(section)
                        onExpandChange()
                    }
                )
        }
    }
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