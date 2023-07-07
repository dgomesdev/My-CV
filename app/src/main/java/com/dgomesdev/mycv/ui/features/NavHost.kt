package com.dgomesdev.mycv.ui.features

import android.content.Context
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
    modifier: Modifier,
    onContactClick: OnContactClick,
    context: Context
) {
    NavHost(
        navController,
        startDestination = "Profile",
        modifier = modifier
    ) {
        val padding = Modifier.padding(8.dp)
        composable(route = "Profile") {
            Profile(padding)
        }
        composable(route = "Work experience") {
            WorkExperiences(padding, context)
        }
        composable(route = "Education") {
            Education(padding, context)
        }
        composable(route = "Foreign languages") {
            ForeignLanguages(padding, context)
        }
        composable(route = "Other") {
            Other(padding,context)
        }
        composable(route = "Contact") {
            Contact(padding, onContactClick)
        }
    }
}