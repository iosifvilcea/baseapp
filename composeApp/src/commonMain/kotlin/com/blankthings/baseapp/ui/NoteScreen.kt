package com.blankthings.baseapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.app_name
import com.blankthings.baseapp.component.TopAppBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NoteScreen(title: String, content: String) {
    Scaffold(
        topBar = { TopAppBar(title = stringResource(Res.string.app_name)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxHeight().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center
            )
            Text(
                text = content,
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun AccountScreenPreview() {
    MaterialTheme {
        NoteScreen("Title", "Content")
    }
}