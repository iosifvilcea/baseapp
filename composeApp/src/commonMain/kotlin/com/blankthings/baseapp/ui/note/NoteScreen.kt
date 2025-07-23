package com.blankthings.baseapp.ui.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blankthings.baseapp.component.AppIcons
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NoteScreen(title: String, content: String, onBackClick: () -> Unit) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(24.dp)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = AppIcons.Close,
                        contentDescription = "",
                    )
                }
            }
        }
        item {
            Column(
                modifier = Modifier.fillMaxHeight().padding(40.dp),
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
}

@Preview
@Composable
fun AccountScreenPreview() {
    MaterialTheme {
        NoteScreen("Title", "Content") {}
    }
}