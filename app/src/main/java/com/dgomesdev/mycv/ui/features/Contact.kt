package com.dgomesdev.mycv.ui.features

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dgomesdev.mycv.R

@Composable
fun Contact(
    modifier: Modifier,
    onContactClick: OnContactClick,
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        ContactCard(modifier = modifier, contactInfo = ContactInfo.Site, onContactClick)
        ContactCard(modifier = modifier, contactInfo = ContactInfo.GitHub, onContactClick)
        ContactCard(modifier = modifier, contactInfo = ContactInfo.LinkedIn, onContactClick)
        ContactCard(modifier = modifier, contactInfo = ContactInfo.Mail, onContactClick)
        ContactCard(modifier = modifier, contactInfo = ContactInfo.WhatsApp, onContactClick)
        ContactCard(modifier = modifier, contactInfo = ContactInfo.PlayStore, onContactClick)
    }
}

@Composable
fun ContactCard(
    modifier: Modifier,
    contactInfo: ContactInfo,
    onContactClick: OnContactClick,
) {
    Column {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onContactClick(contactInfo.link)
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Row {
                    Icon(
                        painter = painterResource(contactInfo.logo),
                        contentDescription = contactInfo.info,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(contactInfo.info, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

sealed class ContactInfo(val logo: Int, val info: String, val link: String) {

    object Site : ContactInfo(
        R.drawable.ic_site,
        "Site",
        "https://dgomesdev.com"
    )

    object GitHub : ContactInfo(
        R.drawable.github_logo,
        "GitHub",
        "https://github.com/dgomesdev"
    )

    object LinkedIn : ContactInfo(
        R.drawable.linkedin_logo,
        "LinkedIn",
        "https://www.linkedin.com/in/gomes-danilo/"
    )

    object Mail : ContactInfo(
        R.drawable.ic_email,
        "E-mail",
        "mailto:danilo.gomes@outlook.fr"
    )

    object WhatsApp : ContactInfo(
        R.drawable.whatsapp_logo,
        "WhatsApp",
        "https://wa.me/5511936186593"
    )

    object PlayStore : ContactInfo(
        R.drawable.google_play,
        "Play store page",
        "https://play.google.com/store/apps/developer?id=Dgomes+Dev"
    )
}