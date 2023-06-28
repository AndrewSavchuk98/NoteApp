package com.savchukandrew.noteapp.domain.usecases

import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Observable

interface GetAllNotesUseCase {

    operator fun invoke(): Observable<List<Note>>
}