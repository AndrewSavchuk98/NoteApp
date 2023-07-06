package com.savchukandrew.noteapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.savchukandrew.noteapp.domain.usecases.AddNoteUseCase
import com.savchukandrew.noteapp.domain.usecases.DeleteNoteUseCase
import com.savchukandrew.noteapp.domain.usecases.GetAllNotesUseCase
import com.savchukandrew.noteapp.domain.usecases.GetNoteByIdUseCase
import com.savchukandrew.noteapp.domain.usecases.UpdateNoteUseCase
import com.savchukandrew.noteapp.presentation.note.NoteViewModel
import com.savchukandrew.noteapp.presentation.notes.NotesViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NotesViewModelFactory @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            NotesViewModel::class.java -> {
                NotesViewModel(getAllNotesUseCase)
            }

            NoteViewModel::class.java -> {
                NoteViewModel(addNoteUseCase, updateNoteUseCase, getNoteByIdUseCase)
            }

            else -> {
                super.create(modelClass)
            }
        }
        return viewModel as T
    }
}