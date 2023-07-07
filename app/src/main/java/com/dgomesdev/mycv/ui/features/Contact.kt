package com.dgomesdev.mycv.ui.features

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.dgomesdev.mycv.R

@Composable
fun Contact(
    modifier: Modifier
) {
    Column {
        ContactCard(modifier = modifier, contactInfo = ContactInfo.Site)
        ContactCard(modifier = modifier, contactInfo = ContactInfo.GitHub)
        ContactCard(modifier = modifier, contactInfo = ContactInfo.LinkedIn)
        ContactCard(modifier = modifier, contactInfo = ContactInfo.Mail)
        ContactCard(modifier = modifier, contactInfo = ContactInfo.WhatsApp)
    }
}

@Composable
fun ContactCard(
    modifier: Modifier,
    contactInfo: ContactInfo
) {
    val context = LocalContext.current
    Column {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier.fillMaxWidth().clickable {
                openWebPage(contactInfo.link, context)
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
                        contentDescription = contactInfo.info
                    )
                }
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = modifier.fillMaxWidth()) {
                    Text(contactInfo.info, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactPreview() {
    Contact(modifier = Modifier.padding(8.dp))
}

fun openWebPage(url: String, context: Context) {
    val webpage: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    startActivity(context, intent, null)
}
sealed class ContactInfo(val logo: Int, val info: String, val link: String) {
    
    object Site : ContactInfo(
        R.drawable.ic_launcher_foreground,
        "Site",
        "https;//dgomesdev.com"
    )    
    object GitHub : ContactInfo(
        R.drawable.ic_launcher_foreground,
        "GitHub",
        "https://github.com/dgomesdev"
    )    
    object LinkedIn : ContactInfo(
        R.drawable.ic_launcher_foreground,
        "LinkedIn",
        "https://www.linkedin.com/in/gomes-danilo/"
    )    
    object Mail : ContactInfo(
        R.drawable.ic_launcher_foreground,
        "Mail",
        "mailto:danilo.gomes@outlook.fr"
    )    
    object WhatsApp : ContactInfo(
        R.drawable.ic_launcher_foreground,
        "WhatsApp",
        "https://wa.me/5511936186593"
    )
}