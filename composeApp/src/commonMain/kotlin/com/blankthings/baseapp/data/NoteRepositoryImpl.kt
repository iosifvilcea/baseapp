package com.blankthings.baseapp.data

import com.blankthings.baseapp.model.Category
import com.blankthings.baseapp.model.Note
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class NoteRepositoryImpl : NoteRepository {
    override val notes: MutableList<Note> = mutableListOf(
        Note(
            id = 1,
            title = "Note 1",
            content = "Content 1",
            category = Category.NOW,
            date = Clock.System.now()
        ),
        Note(
            id = 2,
            title = "Note 2",
            content = "Content 2",
            category = Category.LATER,
            date = Clock.System.now()
        ),
        Note(
            id = 3,
            title = "Note 3",
            content = "Content 3",
            category = Category.MAYBE,
            date = Clock.System.now()
        ),
        Note(
            4,
            "Note 4",
            "Content 4",
            category = Category.NOW,
            date = Clock.System.now()
        ),
        Note(
            5,
            "Note 5",
            "Content 5",
            category = Category.MAYBE,
            date = Clock.System.now()
        ),
        Note(
            6,
            "Note 6",
            "Content 6",
            category = Category.LATER,
            date = Clock.System.now()
        ),
        Note(
            7,
            "Note 7",
            "Content 7",
            category = Category.LATER,
            date = Clock.System.now()
        ),
        Note(
            8,
            "Note 8",
            "Content 8",
            category = Category.NOW,
            date = Clock.System.now()
        ),
    )

    override suspend fun addNote(note: Note) {
        notes.add(note)
    }

    override suspend fun updateNote(note: Note) {
        notes.indexOfFirst { it.id == note.id }.let {
            notes[it] = note
        }
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }

    override fun getNote(noteId: Int): Note {
        return notes.firstOrNull { it.id == noteId } ?: Note(-1, "", "", Category.NONE, Instant.DISTANT_PAST)
    }
}