package com.dgomesdev.mycv.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        val (name, photo, title, resume, softSkills, hardSkills) = createRefs()
        Text(
            "Danilo Gomes",
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(R.drawable.profile_picture) ,
            contentDescription = "Danilo Gomes photo",
            modifier = Modifier.constrainAs(photo) {
                top.linkTo(name.bottom, margin = 16.dp)
                start.linkTo(name.start)
                end.linkTo(name.end)
            }
        )
        Text(
            "Android Developer",
            modifier = Modifier.constrainAs(title) {
            top.linkTo(photo.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
        },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            stringResource(R.string.resume),
            modifier = Modifier.constrainAs(resume) {
                top.linkTo(title.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            textAlign = TextAlign.Justify
        )
        Column(
            modifier = Modifier.constrainAs(softSkills) {
                top.linkTo(resume.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            }
        ) {
            Text("Soft skills",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(" • Active listening")
            Text(" • Client relations")
            Text(" • Teamwork")
            Text(" • Adaptability")
            Text(" • Determination")
        }
        Column(
            modifier = Modifier.constrainAs(hardSkills) {
                top.linkTo(softSkills.bottom, margin = 16.dp)
                start.linkTo(parent.start)
            }
        ) {
            Text("Hard skills",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(" • Android Studio")
            Text(" • Kotlin")
            Text(" • Java")
            Text(" • SQL")
            Text(" • Git")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(modifier = Modifier)
}