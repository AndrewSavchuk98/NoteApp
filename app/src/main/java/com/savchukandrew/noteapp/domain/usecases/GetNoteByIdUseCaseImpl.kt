package com.savchukandrew.noteapp.domain.usecases

import com.savchukandrew.noteapp.domain.NotesRepository
import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Observable
import javax.inject.Inject

class GetNoteByIdUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : GetNoteByIdUseCase {
    override fun invoke(noteId: Int): Observable<Note> {
        return repository.getNoteById(noteId)
    }
}