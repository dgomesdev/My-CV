package com.dgomesdev.mycv.ui.features

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
//noinspection UsingMaterialAndMaterial3Libraries
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dgomesdev.mycv.R
import com.dgomesdev.mycv.model.CvData
import com.dgomesdev.mycv.ui.theme.DgomesDevGreen
import kotlinx.coroutines.launch

typealias OnContactClick = (String) -> Unit
typealias OnLanguageChange = (String) -> Unit

@Composable
fun CVApp(
    onContactClick: OnContactClick,
    onLanguageChange: OnLanguageChange,
    cvData: CvData
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    var sections by remember {
        mutableStateOf(Pair("Profile", context.getString(R.string.profile)))
    }
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
                },
                onLanguageChange = onLanguageChange
            )
        },
        drawerBackgroundColor = Color.Black,
        drawerContent = {
            Image(
                painter = painterResource(R.drawable.dgomesdev_logo_new),
                contentDescription = "DGomes Dev logo"
            )
            Sections(
                onNavigate = { navController.navigate(it) },
                onExpandChange = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                },
                onScreenChange = { sections = it }
            )
        },
        bottomBar = {
            CVBottomBar(
                onNavigate = { navController.navigate(it) },
                sections = sections
            )
        }
    ) {
        CVNavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            onContactClick,
            cvData
        )
    }
}

@Composable
fun CVTopBar(
    onNavigationIconClick: () -> Unit,
    onLanguageChange: OnLanguageChange,
) {
    var expandedMenu by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        backgroundColor = DgomesDevGreen,
        title = { Text(stringResource(R.string.app_name), color = Color.Black) },
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClick
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.cv_sections),
                    tint = Color.Black
                )
            }
        },
        actions = {
            IconButton(onClick = { expandedMenu = !expandedMenu }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_language),
                    contentDescription = stringResource(R.string.language_options),
                    tint = Color.Black
                )
                LanguageMenu(
                    expandedMenu = expandedMenu,
                    onExpandChange = { expandedMenu = !expandedMenu },
                    onLanguageChange = onLanguageChange
                )
            }
        }
    )
}

@Composable
fun Sections(
    onNavigate: (String) -> Unit,
    onExpandChange: () -> Unit,
    onScreenChange: (Pair<String, String>) -> Unit,
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
            val screen = Pair(section.key, section.value)
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
                            onScreenChange(screen)
                            onExpandChange()
                        },
                    Color.Black
                )
            }
        }
    }
}

@Composable
fun LanguageMenu(
    expandedMenu: Boolean,
    onExpandChange: () -> Unit,
    onLanguageChange: OnLanguageChange,
) {
    DropdownMenu(expanded = expandedMenu, onDismissRequest = { onExpandChange() }) {
        DropdownMenuItem(
            { Text("English") },
            onClick = { onExpandChange(); onLanguageChange("en") })
        DropdownMenuItem(
            { Text("Français") },
            onClick = { onExpandChange(); onLanguageChange("fr") })
        DropdownMenuItem(
            { Text("Português") },
            onClick = { onExpandChange(); onLanguageChange("pt") })
    }
}

@Composable
fun CVBottomBar(
    onNavigate: (String) -> Unit,
    sections: Pair<String, String>,
) {
    var currentScreen by rememberSaveable {
        mutableIntStateOf(0)
    }
    val tabs = listOf(sections.first, "Contact")
    NavigationBar(
        containerColor = DgomesDevGreen
    ) {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = currentScreen == index,
                onClick = { onNavigate(tab); currentScreen = index },
                icon = {
                    if (index == 0) Icon(
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = tab,
                        tint = Color.Black
                    )
                    else Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Contact",
                        tint = Color.Black
                    )
                },
                label = {
                    Text(
                        if (index == 0) sections.second
                        else stringResource(R.string.contact),
                        color = Color.Black
                    )
                }
            )
        }
    }
}