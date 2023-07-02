package com.savchukandrew.noteapp.di

import com.savchukandrew.noteapp.domain.usecases.AddNoteUseCase
import com.savchukandrew.noteapp.domain.usecases.AddNoteUseCaseImpl
import com.savchukandrew.noteapp.domain.usecases.DeleteNoteUseCase
import com.savchukandrew.noteapp.domain.usecases.DeleteNoteUseCaseImpl
import com.savchukandrew.noteapp.domain.usecases.GetAllNotesUseCase
import com.savchukandrew.noteapp.domain.usecases.GetAllNotesUseCaseImpl
import com.savchukandrew.noteapp.domain.usecases.GetNoteByIdUseCase
import com.savchukandrew.noteapp.domain.usecases.GetNoteByIdUseCaseImpl
import com.savchukandrew.noteapp.domain.usecases.UpdateNoteUseCase
import com.savchukandrew.noteapp.domain.usecases.UpdateNoteUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {

    @Binds
    fun bindGetAllNotesUseCase(useCase: GetAllNotesUseCaseImpl): GetAllNotesUseCase

    @Binds
    fun bindAddNoteUseCase(useCase: AddNoteUseCaseImpl): AddNoteUseCase

    @Binds
    fun bindDeleteNoteUseCase(useCase: DeleteNoteUseCaseImpl): DeleteNoteUseCase

    @Binds
    fun bindUpdateNotesUseCase(useCase: UpdateNoteUseCaseImpl): UpdateNoteUseCase

    @Binds
    fun bindGetNoteByIdNotesUseCase(useCase: GetNoteByIdUseCaseImpl): GetNoteByIdUseCase

}