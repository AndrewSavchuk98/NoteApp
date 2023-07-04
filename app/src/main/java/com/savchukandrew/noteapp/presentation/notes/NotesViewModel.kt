package com.savchukandrew.noteapp.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savchukandrew.noteapp.domain.models.Note
import com.savchukandrew.noteapp.domain.usecases.AddNoteUseCase
import com.savchukandrew.noteapp.domain.usecases.DeleteNoteUseCase
import com.savchukandrew.noteapp.domain.usecases.GetAllNotesUseCase
import com.savchukandrew.noteapp.presentation.notes.models.NoteUi
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _state: MutableLiveData<NotesUiState> = MutableLiveData()
    val state: LiveData<NotesUiState> = _state

    private val compositeDisposable = CompositeDisposable()

    init {
        val disposable = getAllNotesUseCase.invoke()
            .map { list ->
                list.map {
                    NoteUi(
                        id = it.id,
                        title = it.title,
                        text = it.text,
                        date = it.date.toString()
                    )
                }
            }
            .subscribe {
                val currentState = _state.value ?: NotesUiState()
                _state.value = currentState.copy(notes = it)
            }
        compositeDisposable.add(disposable)

    }

    fun addNote(note: Note) {
        val disposable = addNoteUseCase.invoke(note).subscribe()
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

data class NotesUiState(
    var notes: List<NoteUi> = emptyList()
)