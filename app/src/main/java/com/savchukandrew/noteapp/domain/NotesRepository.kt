package com.savchukandrew.noteapp.domain

import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface NotesRepository {

    fun getAllNotes(): Flowable<List<Note>>

    fun getNoteById(id: Int): Observable<Note>

    fun addNote(note: Note): Completable

    fun remoteNote(note: Note): Completable

    fun updateNote(note: Note): Completable
}