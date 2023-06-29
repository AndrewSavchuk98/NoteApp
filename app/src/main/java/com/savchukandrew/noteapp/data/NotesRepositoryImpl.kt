package com.savchukandrew.noteapp.data

import com.savchukandrew.noteapp.domain.NotesRepository
import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val dataSource: NotesDataSource
) : NotesRepository {
    override fun getAllNotes(): Flowable<List<Note>> {
        return dataSource.getAllNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getNoteById(id: Int): Observable<Note> {
        return dataSource.getNoteById(id).toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addNote(note: Note): Completable {
        return dataSource.addNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun remoteNote(note: Note): Completable {
        return dataSource.remoteNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateNote(note: Note): Completable {
        return dataSource.updateNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}