package com.savchukandrew.noteapp.data.local

import com.savchukandrew.noteapp.core.Mapper
import com.savchukandrew.noteapp.data.NotesDataSource
import com.savchukandrew.noteapp.domain.models.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class RoomNotesDataSource @Inject constructor(
    private val dao: NotesDAO,
    private val mapperToDomain: Mapper<NoteEntity, Note>,
    private val mapperToRoom: Mapper<Note, NoteEntity>
) : NotesDataSource {

    override fun getAllNotes(): Flowable<List<Note>> {
        return dao.getAllNotes().map {
            it.map { note -> mapperToDomain.map(note) }
        }
    }

    override fun getNoteById(id: Int): Flowable<Note> {
        return dao.getNoteById(id).map {
            mapperToDomain.map(it)
        }
    }

    override fun addNote(note: Note): Completable {
        return dao.insertNote(mapperToRoom.map(note))
    }

    override fun remoteNote(note: Note): Completable {
        return dao.deleteNote(mapperToRoom.map(note))
    }

    override fun updateNote(note: Note): Completable {
        return dao.updateNote(mapperToRoom.map(note))
    }

}