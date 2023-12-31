package com.savchukandrew.noteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NotesRoomDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NotesDAO
}