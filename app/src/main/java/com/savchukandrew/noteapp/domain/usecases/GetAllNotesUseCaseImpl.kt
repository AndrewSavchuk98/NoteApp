package com.savchukandrew.noteapp.domain.usecases

import com.savchukandrew.noteapp.domain.NotesRepository
import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Flowable
import javax.inject.Inject

class GetAllNotesUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : GetAllNotesUseCase {

    override operator fun invoke(): Flowable<List<Note>> {
        return repository.getAllNotes()
    }
}