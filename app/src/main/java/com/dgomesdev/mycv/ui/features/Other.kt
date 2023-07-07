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
fun Other(
    modifier: Modifier
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        OtherExperience(modifier = modifier, experience = Experience.Certifications)
        OtherExperience(modifier = modifier, experience = Experience.Volunteering)
        OtherExperience(modifier = modifier, experience = Experience.Hobbies)
    }
}

@Composable
fun OtherExperience(
    modifier: Modifier,
    experience: Experience
) {
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
        var expanded by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(experience.name, modifier = modifier.weight(3f))
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
                Text(experience.firstTopic)
                Text(experience.secondTopic, modifier = Modifier.padding(top = 16.dp))
                Text(experience.thirdTopic, modifier = Modifier.padding(top = 16.dp))
                Text(experience.fourthTopic, modifier = Modifier.padding(top = 16.dp))
            }
        }
    }
}

sealed class Experience(
    val name: String,
    val firstTopic: String,
    val secondTopic: String,
    val thirdTopic: String,
    val fourthTopic: String
) {

    object Certifications : Experience(
        "Certifications",
        " • Formação Android Developer - DIO - May 2023",
        " • Santander Bootcamp Mobile Developer - DIO - April 2023",
        " • Kotlin Experience - DIO - December 2022",
        " • Potência Tech powered by iFood - Java Beginners - DIO - November 2022"
    )

    object Volunteering : Experience(
        "Volunteering",
        " • Fans' support and orientation - Women's World Cup 2019 in France",
        " • Fans' support and orientation - Men's World Cup 2018 in Russia",
        " • Fans' support and orientation - Men's European Championship 2016 in France",
        " • Portuguese teacher for refugee Haitian women in 2011 in Brazil"
    )

    object Hobbies : Experience(
        "Curiosities about me",
        " • Chess champion in High School",
        " • Travelled the Trans-Siberian Railway",
        " • Plays guitar since was 10",
        " • Favorite sport: swimming"
    )
}