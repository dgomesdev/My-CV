package com.dgomesdev.mycv.ui.features

import android.content.Context
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
import com.dgomesdev.mycv.R

@Composable
fun Other(
    modifier: Modifier,
    context: Context
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        OtherExperience(modifier = modifier, experience = Experience.Certifications(context))
        OtherExperience(modifier = modifier, experience = Experience.Volunteering(context))
        OtherExperience(modifier = modifier, experience = Experience.Hobbies(context))
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

    class Certifications(context: Context) : Experience(
        context.getString(R.string.certifications),
        " • Formation Android Developer - DIO - 05/2023",
        " • Santander Bootcamp Mobile Developer - DIO - 04/2023",
        " • Kotlin Experience - DIO - 12/2022",
        " • Potência Tech powered by iFood - Java Beginners - DIO - 11/2022"
    )

    class Volunteering(context: Context) : Experience(
        context.getString(R.string.volunteering),
        context.getString(R.string.women_s_world_cup),
        context.getString(R.string.men_s_world_cup),
        context.getString(R.string.uefa_euro),
        context.getString(R.string.portuguese_teacher)
    )

    class Hobbies(context: Context) : Experience(
        context.getString(R.string.curiosities),
        context.getString(R.string.chess_champion),
        context.getString(R.string.trans_siberian_railway),
        context.getString(R.string.plays_guitar),
        context.getString(R.string.swimming)
    )
}