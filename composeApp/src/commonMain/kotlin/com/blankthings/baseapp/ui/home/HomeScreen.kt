package com.blankthings.baseapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blankthings.baseapp.component.BaCard
import com.blankthings.baseapp.model.Note
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(notes: List<Note>, onNoteClicked: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Text(
                text = "Home Screen",
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                textAlign = TextAlign.Center
            )
        }
        items(notes) { note ->
            BaCard(note.id, note.title, note.content, onNoteClicked)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        val notes = mutableListOf(Note(0, "title", "content"))
        HomeScreen(notes) {}
    }
}