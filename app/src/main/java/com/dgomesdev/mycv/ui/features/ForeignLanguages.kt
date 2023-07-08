package com.dgomesdev.mycv.ui.features

import android.content.Context
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
import com.dgomesdev.mycv.R

@Composable
fun ForeignLanguages(
    modifier: Modifier,
    context: Context
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        LanguageCard(modifier = modifier, Language.Portuguese(context))
        LanguageCard(modifier = modifier, Language.French(context))
        LanguageCard(modifier = modifier, Language.English(context))
        LanguageCard(modifier = modifier, Language.Spanish(context))
        LanguageCard(modifier = modifier, Language.Italian(context))
        LanguageCard(modifier = modifier, Language.German(context))
        LanguageCard(modifier = modifier, Language.Russian(context))
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

sealed class Language(val flag: String, val language: String, val level: String) {

    class English(context: Context) : Language(
        "\uD83C\uDDFA\uD83C\uDDF8",
        context.getString(R.string.english),
        context.getString(R.string.advanced)
    )

    class French(context: Context) : Language(
        "\uD83C\uDDEB\uD83C\uDDF7",
        context.getString(R.string.french),
        context.getString(R.string.fluent)
    )

    class Portuguese(context: Context) : Language(
        "\uD83C\uDDE7\uD83C\uDDF7",
        context.getString(R.string.portuguese),
        context.getString(R.string.native_language)
    )

    class Spanish(context: Context) : Language(
        "\uD83C\uDDEA\uD83C\uDDF8",
        context.getString(R.string.spanish),
        context.getString(R.string.intermediary)
    )

    class Italian(context: Context) : Language(
        "\uD83C\uDDEE\uD83C\uDDF9",
        context.getString(R.string.italian),
        context.getString(R.string.intermediary)
    )

    class German(context: Context) : Language(
        "\uD83C\uDDE9\uD83C\uDDEA",
        context.getString(R.string.german),
        context.getString(R.string.basic)
    )

    class Russian(context: Context) : Language(
        "\uD83C\uDDF7\uD83C\uDDFA",
        context.getString(R.string.russian),
        context.getString(R.string.basic)
    )
}