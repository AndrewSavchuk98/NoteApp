package com.savchukandrew.noteapp.domain.usecases

import com.savchukandrew.noteapp.domain.NotesRepository
import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Completable
import javax.inject.Inject

class AddNoteUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : AddNoteUseCase {

    override fun invoke(note: Note): Completable {
        return repository.addNote(note)
    }
}