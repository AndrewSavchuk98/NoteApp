package com.savchukandrew.noteapp.di

import com.savchukandrew.noteapp.data.NotesDataSource
import com.savchukandrew.noteapp.data.NotesRepositoryImpl
import com.savchukandrew.noteapp.data.local.RoomNotesDataSource
import com.savchukandrew.noteapp.domain.NotesRepository
import com.savchukandrew.noteapp.domain.usecases.AddNoteUseCase
import com.savchukandrew.noteapp.domain.usecases.AddNoteUseCaseImpl
import com.savchukandrew.noteapp.domain.usecases.GetAllNotesUseCase
import com.savchukandrew.noteapp.domain.usecases.GetAllNotesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindNotesDataSource(notesDataSource: RoomNotesDataSource): NotesDataSource

    @Binds
    fun bindGetAllNotesUseCase(useCase: GetAllNotesUseCaseImpl): GetAllNotesUseCase

    @Binds
    fun bindAddNotesUseCase(useCase: AddNoteUseCaseImpl): AddNoteUseCase

    @Binds
    fun bindNotesRepository(notesRepositoryImpl: NotesRepositoryImpl): NotesRepository
}