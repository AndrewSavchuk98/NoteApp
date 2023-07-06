package com.savchukandrew.noteapp.presentation.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savchukandrew.noteapp.core.log
import com.savchukandrew.noteapp.domain.models.Note
import com.savchukandrew.noteapp.domain.usecases.AddNoteUseCase
import com.savchukandrew.noteapp.domain.usecases.GetNoteByIdUseCase
import com.savchukandrew.noteapp.domain.usecases.UpdateNoteUseCase
import com.savchukandrew.noteapp.presentation.notes.models.NoteUi
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) : ViewModel() {

    private val _note: MutableLiveData<NoteUi> = MutableLiveData()
    val note: LiveData<NoteUi> = _note

    val state: MutableLiveData<NoteState> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun addNote(noteUi: NoteUi) {
        log("Note in addNote $noteUi")
        val note = Note(
            id = noteUi.id,
            title = noteUi.title,
            text = noteUi.text,
            date = noteUi.date
        )
        val disposable = addNoteUseCase(note)
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun getNoteByID(noteId: Int) {
        log("NoteId in GetNoteById $noteId")

        val disposable = getNoteByIdUseCase(noteId)
            .map {
                NoteUi(
                    id = it.id,
                    title = it.title,
                    text = it.text,
                    date = it.date
                )
            }
            .subscribe {
                _note.value = it
            }
        compositeDisposable.add(disposable)
    }

    fun updateNote(noteUi: NoteUi) {
        log("Note in updateNote $noteUi")

        val note = Note(
            id = noteUi.id,
            title = noteUi.title,
            text = noteUi.text,
            date = noteUi.date
        )
        val disposable = updateNoteUseCase(note).subscribe()
        compositeDisposable.add(disposable)
    }

    fun saveNote(note: Note) {
        val noteLive = Note(
            id = state.value!!.id,
            title = state.value!!.title,
            text = state.value!!.text,
            date = note.date
        )
        val disposable = if (note.id != 0) {
            updateNoteUseCase(noteLive).subscribe()
        } else {
            addNoteUseCase(note).subscribe()
        }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

data class NoteState(
    var id: Int = 0,
    var title: String,
    var text: String
)