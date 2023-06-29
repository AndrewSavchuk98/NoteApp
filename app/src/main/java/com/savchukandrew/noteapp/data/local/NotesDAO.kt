package com.savchukandrew.noteapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NotesDAO {

    @Query("select * from note")
    fun getAllNotes(): Flowable<List<NoteEntity>>

    @Query("select * from note where id = :id")
    fun getNoteById(id: Int): Flowable<NoteEntity>

    @Insert
    fun insertNote(noteEntity: NoteEntity): Completable

    @Delete
    fun deleteNote(noteEntity: NoteEntity): Completable

    @Update
    fun updateNote(noteEntity: NoteEntity): Completable
}