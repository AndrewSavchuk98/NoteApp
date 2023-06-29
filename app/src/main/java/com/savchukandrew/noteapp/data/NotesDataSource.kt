package com.savchukandrew.noteapp.data

import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Completable
import io.reactivex.Flowable

interface NotesDataSource {

    fun getAllNotes(): Flowable<List<Note>>

    fun getNoteById(id: Int): Flowable<Note>

    fun addNote(note: Note): Completable

    fun remoteNote(note: Note): Completable

    fun updateNote(note: Note): Completable
}