package com.dgomesdev.mycv.ui.features

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun CVNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController,
        startDestination = "Profile",
        modifier = modifier
    ) {
        val padding = Modifier.padding(16.dp)
        composable(route = "Profile") {
            Profile(padding)
        }
        composable(route = "WorkExperience") {
            WorkExperience(padding)
        }
        composable(route = "Education") {
            Education(padding)
        }
        composable(route = "Languages") {
            Languages(padding)
        }
        composable(route = "Other") {
            Other(padding)
        }
        composable(route = "Contact") {
            Contact(padding)
        }
    }
}