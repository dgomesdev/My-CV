package com.dgomesdev.mycv.ui.features

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkExperiences(
    modifier: Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Experience(modifier = modifier, job = JobExperience.BusinessFrance)
        Experience(modifier = modifier, job = JobExperience.Rodafuso)
        Experience(modifier = modifier, job = JobExperience.Stracau)
        Experience(modifier = modifier, job = JobExperience.Businove)
    }
}

@Composable
fun Experience(
    modifier: Modifier,
    job: JobExperience
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            Column(
                modifier = modifier.weight(1f)
            ) {
                Text("${job.job} at ${job.company} - ${job.beginDate} - ${job.endDate}")
                if (expanded) {
                    Column(modifier = Modifier.padding(top = 16.dp)) {
                        Text(job.location)
                        Text(job.description, modifier = Modifier.padding(top = 16.dp))
                    }
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "show less"
                    else "show more"
                )
            }
        }
    }
}

sealed class JobExperience(val job: String, val company: String, val beginDate: String, val endDate: String, val location: String, val description: String) {
    object BusinessFrance : JobExperience(
        "International business developer",
        "Business France",
        "09/2021",
        "09/2023",
        "Sao Paulo, Brazil",
        "Tech"
    )

    object Rodafuso : JobExperience(
        "International sales",
        "Rodafuso",
        "01/2021",
        "09/2021",
        "Sao Bernardo do Campo, Brazil",
        "Industrial parts"
    )

    object Stracau : JobExperience(
        "International sales",
        "Stracau Valves France",
        "10/2019",
        "09/2020",
        "Genas, France",
        "Industrial valves"
    )

    object Businove : JobExperience(
        "International development assistant",
        "Businove Innovation Consulting",
        "04/2019",
        "09/2019",
        "Lyon, France",
        "Consulting firm"
    )
}