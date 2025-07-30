package com.blankthings.baseapp.data

import com.blankthings.baseapp.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun getNote(noteId: Long): Note
    fun getAllNotes(): Flow<List<Note>>
}