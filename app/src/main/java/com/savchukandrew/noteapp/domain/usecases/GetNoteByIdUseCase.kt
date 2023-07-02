package com.savchukandrew.noteapp.domain.usecases

import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Observable

interface GetNoteByIdUseCase {

    operator fun invoke(noteId: Int): Observable<Note>
}