package com.dgomesdev.mycv.ui.features

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dgomesdev.mycv.R
import com.dgomesdev.mycv.ui.theme.DgomesDevGreen
import kotlinx.coroutines.launch

typealias OnContactClick = (String) -> Unit

@Composable
fun CVApp(
    onContactClick: OnContactClick,
    context: Context
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background,
        scaffoldState = scaffoldState,
        topBar = {
            CVTopBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerBackgroundColor = Color.Black,
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
        bottomBar = {
            CVBottomBar(
                onNavigate = { navController.navigate(it) },
                screen = "Profile"
            )
        }
    ) {
        CVNavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            onContactClick,
            context
        )
    }
}

@Composable
fun CVTopBar(
    onNavigationIconClick: () -> Unit
) {
    var expandedMenu by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        backgroundColor = DgomesDevGreen,
        title = { Text(stringResource(R.string.app_name)) },
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClick
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.cv_sections)
                )
            }
        },
        actions = {
            IconButton(onClick = { expandedMenu = !expandedMenu }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = stringResource(R.string.language_options)
                )
                LanguageMenu(
                    expandedMenu = expandedMenu,
                    onExpandChange = { expandedMenu = !expandedMenu }
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
        val sections = mapOf(
            "Profile" to stringResource(R.string.profile),
            "Work experience" to stringResource(R.string.work_experience),
            "Education" to stringResource(R.string.education),
            "Foreign languages" to stringResource(R.string.foreign_languages),
            "Other" to stringResource(R.string.other)
        )
        for (section in sections) {
            Row(
                modifier = Modifier
                    .background(color = DgomesDevGreen)
                    .border(BorderStroke(1.dp, Color.Black))
            ) {
                Text(
                    section.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            onNavigate(section.key)
                            onExpandChange()
                        }
                )
            }
        }
    }
}

@Composable
fun LanguageMenu(
    expandedMenu: Boolean,
    onExpandChange: () -> Unit
) {
    DropdownMenu(expanded = expandedMenu, onDismissRequest = { onExpandChange() }) {
        DropdownMenuItem({Text(stringResource(R.string.english))}, onClick = { onExpandChange() })
        DropdownMenuItem({Text(stringResource(R.string.french))}, onClick = { onExpandChange() })
        DropdownMenuItem({Text(stringResource(R.string.portuguese))}, onClick = { onExpandChange() })
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
    NavigationBar(
        containerColor = DgomesDevGreen
    ) {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = currentScreen == index,
                onClick = { onNavigate(tab); currentScreen = index },
                icon = {
                    if (index == 0) Icon(imageVector = Icons.Default.List, contentDescription = tab)
                    else Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Contact"
                    )
                },
                label = {
                    Text(
                        if (index == 0) tab
                        else stringResource(R.string.contact)
                    )
                }
            )
        }
    }
}