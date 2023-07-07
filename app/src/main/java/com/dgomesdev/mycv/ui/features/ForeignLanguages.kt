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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ForeignLanguages(
    modifier: Modifier
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        LanguageCard(modifier = modifier, Language.Portuguese)
        LanguageCard(modifier = modifier, Language.French)
        LanguageCard(modifier = modifier, Language.English)
        LanguageCard(modifier = modifier, Language.Spanish)
        LanguageCard(modifier = modifier, Language.Italian)
        LanguageCard(modifier = modifier, Language.German)
        LanguageCard(modifier = modifier, Language.Russian)
    }
}

@Composable
fun LanguageCard(
    modifier: Modifier,
    language: Language
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

@Preview(showBackground = true)
@Composable
fun LanguagePreview() {
    ForeignLanguages(modifier = Modifier.padding(8.dp))
}

sealed class Language(val flag: String, val language: String, val level: String) {

    object English : Language(
        "\uD83C\uDDFA\uD83C\uDDF8",
        "English",
        "Advanced"
    )

    object French : Language(
        "\uD83C\uDDEB\uD83C\uDDF7",
        "French",
        "Fluent"
    )

    object Portuguese : Language(
        "\uD83C\uDDE7\uD83C\uDDF7",
        "Portuguese",
        "Native language"
    )

    object Spanish : Language(
        "\uD83C\uDDEA\uD83C\uDDF8",
        "Spanish",
        "Intermediary"
    )

    object Italian : Language(
        "\uD83C\uDDEE\uD83C\uDDF9",
        "Italian",
        "Intermediary"
    )

    object German : Language(
        "\uD83C\uDDE9\uD83C\uDDEA",
        "German",
        "Basic"
    )

    object Russian : Language(
        "\uD83C\uDDF7\uD83C\uDDFA",
        "Russian",
        "Basic"
    )
}