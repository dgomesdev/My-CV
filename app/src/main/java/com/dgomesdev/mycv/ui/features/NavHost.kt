package com.dgomesdev.mycv.ui.features

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dgomesdev.mycv.model.CvData

@Composable
fun CVNavHost(
    navController: NavHostController,
    modifier: Modifier,
    onContactClick: OnContactClick,
    cvData: CvData
) {
    NavHost(
        navController,
        startDestination = "Profile",
        modifier = modifier
    ) {
        val padding = Modifier.padding(8.dp)
        composable(route = "Profile") {
            Profile(padding, cvData.profile)
        }
        composable(route = "Work experience") {
            WorkExperiences(padding, cvData.workExperience)
        }
        composable(route = "Education") {
            Education(padding, cvData.education)
        }
        composable(route = "Foreign languages") {
            ForeignLanguages(padding, cvData.foreignLanguages)
        }
        composable(route = "Other") {
            Other(padding, cvData.others)
        }
        composable(route = "Contact") {
            Contact(padding, onContactClick)
        }
    }
}