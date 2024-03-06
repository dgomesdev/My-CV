package com.dgomesdev.mycv.ui.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dgomesdev.mycv.model.ForeignLanguage

@Composable
fun ForeignLanguages(
    modifier: Modifier,
    languagesList: List<ForeignLanguage>
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        for (language in languagesList) {
                LanguageCard(modifier, language)
            }
    }
}

@Composable
fun LanguageCard(
    modifier: Modifier,
    language: ForeignLanguage
) {
    Column {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier
                ) {
                    Text(language.flag)
                    Spacer(Modifier.padding(horizontal = 8.dp))
                    Text(language.language)
                }
                Text(language.level, modifier)
            }
        }
    }
}