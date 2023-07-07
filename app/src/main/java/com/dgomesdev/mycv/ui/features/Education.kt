package com.dgomesdev.mycv.ui.features

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Education(
    modifier: Modifier
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Course(modifier = modifier, course = CourseExperience.Master)
        Course(modifier = modifier, course = CourseExperience.Exchange)
        Course(modifier = modifier, course = CourseExperience.Bachelor)
        Course(modifier = modifier, course = CourseExperience.Technical)
    }
}


@Composable
fun Course(
    modifier: Modifier,
    course: CourseExperience
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${course.degree} - ${course.course}", modifier = modifier.weight(3f))
            IconButton(onClick = { expanded = !expanded }, modifier = modifier.weight(1f)) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "show less"
                    else "show more"
                )
            }
        }
        if (expanded) {
            Column(modifier = modifier.padding(8.dp)) {
                Text("${course.beginDate} - ${course.endDate}")
                Text(course.institution, modifier = Modifier.padding(top = 16.dp))
                Text(course.location, modifier = Modifier.padding(top = 16.dp))
            }
        }
    }
}

sealed class CourseExperience(
    val degree: String,
    val course: String,
    val institution: String,
    val beginDate: String,
    val endDate: String,
    val location: String
) {
    object Master : CourseExperience(
        "Master degree",
        "International relations",
        "Université Jean Moulin Lyon 3",
        "09/2016",
        "09/2019",
        "Lyon, France"
    )

    object Exchange : CourseExperience(
        "Graduate",
        "International Law",
        "MSAL - Moscow State University of Law",
        "09/2017",
        "06/2018",
        "Moscow, Russia"
    )

    object Bachelor : CourseExperience(
        "Bachelor",
        "Law and Political Science",
        "Université Jean Moulin Lyon 3",
        "09/2013",
        "07/2016",
        "Lyon, France"
    )

    object Technical : CourseExperience(
        "Technical degree integrated to the High school",
        "Informatics",
        "Federal Institute of Education, Science and Technology",
        "02/2009",
        "11/2011",
        "Manaus, Brazil"
    )
}
