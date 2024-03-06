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
import com.dgomesdev.mycv.model.Education

@Composable
fun Education(
    modifier: Modifier,
    educationList: List<Education>
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        for (course in educationList) {
                Course(modifier, course)
            }
    }
}


@Composable
fun Course(
    modifier: Modifier,
    course: Education
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
            Text(course.degreeName, modifier = modifier.weight(3f))
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
                Text("${course.beginningDate} - ${course.endDate}")
                Text(course.institution, modifier = Modifier.padding(top = 16.dp))
                Text(course.location, modifier = Modifier.padding(top = 16.dp))
            }
        }
    }
}