package com.savchukandrew.noteapp.di

import android.content.Context
import androidx.room.Room
import com.savchukandrew.noteapp.data.local.NotesDAO
import com.savchukandrew.noteapp.data.local.NotesRoomDatabase
import dagger.Module
import dagger.Provides


@Module
class RoomModule{

    @Provides
    fun provideRoomDataBase(context: Context) =
        Room.databaseBuilder(
            context,
            NotesRoomDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    fun provideNotesDao(database: NotesRoomDatabase): NotesDAO {
        return database.getNoteDao()
    }

    companion object {
        private const val DATABASE_NAME = "notes.db"
    }
}