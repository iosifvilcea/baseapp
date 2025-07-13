package com.blankthings.baseapp.data

import com.blankthings.baseapp.model.Note

class NoteRepositoryImpl : NoteRepository {
    override val notes: MutableList<Note> = mutableListOf(
        Note(1, "Note 1", "Content 1"),
        Note(2, "Note 2", "Content 2"),
        Note(3, "Note 3", "Content 3"),
        Note(4, "Note 4", "Content 4"),
        Note(5, "Note 5", "Content 5"),
        Note(6, "Note 6", "Content 6"),
        Note(7, "Note 7", "Content 7"),
        Note(8, "Note 8", "Content 8"),
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
}