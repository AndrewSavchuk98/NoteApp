package com.savchukandrew.noteapp.domain.usecases

import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Observable
import javax.inject.Inject

class GetAllNotesUseCaseImpl @Inject constructor(

) : GetAllNotesUseCase {

    override operator fun invoke(): Observable<List<Note>> {
        TODO("Create implementation")
    }
}