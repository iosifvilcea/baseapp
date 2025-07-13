package com.blankthings.baseapp.data

import com.blankthings.baseapp.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    val notes: List<Note>
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
}