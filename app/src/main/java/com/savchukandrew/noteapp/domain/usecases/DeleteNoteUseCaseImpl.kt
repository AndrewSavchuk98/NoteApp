package com.savchukandrew.noteapp.domain.usecases

import com.savchukandrew.noteapp.domain.NotesRepository
import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Completable
import javax.inject.Inject

class DeleteNoteUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : DeleteNoteUseCase {
    override fun invoke(note: Note): Completable {
        return repository.remoteNote(note)
    }
}