package com.savchukandrew.noteapp.domain.usecases

import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Flowable

interface GetAllNotesUseCase {

    operator fun invoke(): Flowable<List<Note>>
}