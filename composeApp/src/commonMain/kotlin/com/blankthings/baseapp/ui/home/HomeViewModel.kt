package com.blankthings.baseapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.blankthings.baseapp.data.NoteRepository
import com.blankthings.baseapp.data.UserDataRepository
import com.blankthings.baseapp.ui.settings.SettingsViewModel
import kotlin.reflect.KClass

class HomeViewModel(private val noteRepository: NoteRepository): ViewModel() {
    fun getNotes() = noteRepository.notes

    companion object {
        fun provideFactory(
            noteRepository: NoteRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return HomeViewModel(noteRepository) as T
            }
        }
    }
}