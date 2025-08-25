package com.blankthings.baseapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.blankthings.baseapp.data.NoteRepository
import com.blankthings.baseapp.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class HomeViewModel(private val noteRepository: NoteRepository): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            noteRepository
                .getAllNotes()
                .collect {
                    _uiState.value = HomeUiState(it)
                }
        }
    }

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

data class HomeUiState(
    val notes: List<Note> = listOf()
)