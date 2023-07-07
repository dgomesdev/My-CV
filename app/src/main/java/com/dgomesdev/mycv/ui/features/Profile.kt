package com.dgomesdev.mycv.ui.features

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dgomesdev.mycv.R

@Composable
fun Profile(
    modifier: Modifier
) {
    ConstraintLayout(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        val (name, photo, title, resume, softSkills, hardSkills) = createRefs()
        Text(
            "Danilo Gomes",
            modifier = modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Danilo Gomes photo",
            modifier = Modifier.constrainAs(photo) {
                top.linkTo(name.bottom, margin = 16.dp)
                start.linkTo(name.start)
                end.linkTo(name.end)
            }
        )
        Text(
            "Android Developer",
            modifier = modifier.constrainAs(title) {
                top.linkTo(photo.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Card(modifier.constrainAs(resume) {
            top.linkTo(title.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            var expanded by remember {
                mutableStateOf(false)
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "About me",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = if (expanded) "show less"
                        else "show more"
                    )
                }
            }
            if (expanded) {
                Text(
                    stringResource(R.string.resume),
                    modifier = modifier.padding(8.dp),
                    textAlign = TextAlign.Justify
                )
            }
        }
        Card(modifier.constrainAs(softSkills) {
            top.linkTo(resume.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            var expanded by remember {
                mutableStateOf(false)
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Soft skills",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = if (expanded) "show less"
                        else "show more"
                    )
                }
            }
            if (expanded) {
                Column(modifier.padding(start = 8.dp, bottom = 8.dp)) {
                    Text(" • Active listening")
                    Text(" • Client relations")
                    Text(" • Teamwork")
                    Text(" • Adaptability")
                    Text(" • Determination")
                }
            }
        }
        Card(modifier.constrainAs(hardSkills) {
            top.linkTo(softSkills.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            var expanded by remember {
                mutableStateOf(false)
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Hard skills",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = if (expanded) "show less"
                        else "show more"
                    )
                }
            }
            if (expanded) {
                Column(modifier.padding(start = 8.dp, bottom = 8.dp)) {
                    Text(" • Native Android (Kotlin + Java")
                    Text(" • MVVM")
                    Text(" • Unit tests")
                    Text(" • SQL")
                    Text(" • Git")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(modifier = Modifier.padding(8.dp))
}