package com.savchukandrew.noteapp.presentation.note

import android.util.Log
import androidx.lifecycle.ViewModel
import com.savchukandrew.noteapp.domain.models.Note
import com.savchukandrew.noteapp.domain.usecases.AddNoteUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun addNote(note: Note) {
        val disposable = addNoteUseCase.invoke(note).subscribe()
        compositeDisposable.add(disposable)
        Log.d("MainActivity", "Add Called")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}