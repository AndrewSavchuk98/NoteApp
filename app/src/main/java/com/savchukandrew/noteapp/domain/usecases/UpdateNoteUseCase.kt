package com.savchukandrew.noteapp.domain.usecases

import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Completable

interface UpdateNoteUseCase {

    operator fun invoke(note: Note): Completable
}