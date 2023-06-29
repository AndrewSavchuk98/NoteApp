package com.savchukandrew.noteapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.savchukandrew.noteapp.domain.usecases.GetAllNotesUseCase
import com.savchukandrew.noteapp.presentation.notes.NotesViewModel
import javax.inject.Inject

class NotesViewModelFactory @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            NotesViewModel::class.java -> {
                NotesViewModel(getAllNotesUseCase)
            }

            else -> {
                super.create(modelClass)
            }
        }

        return viewModel as T
    }
}