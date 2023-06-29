package com.savchukandrew.noteapp.domain.usecases

import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Completable

interface AddNoteUseCase {

    operator fun invoke(note: Note) : Completable
}