package com.savchukandrew.noteapp.di

import com.savchukandrew.noteapp.core.Mapper
import com.savchukandrew.noteapp.data.local.NoteEntity
import com.savchukandrew.noteapp.data.local.mappers.DomainDataToRoomDataMapper
import com.savchukandrew.noteapp.data.local.mappers.RoomDataToDomainDataMapper
import com.savchukandrew.noteapp.domain.models.Note
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun provideDomainToDataMapper(): Mapper<Note, NoteEntity>{
        return DomainDataToRoomDataMapper()
    }

    @Provides
    fun provideDataToDomainMapper(): Mapper<NoteEntity, Note>{
        return RoomDataToDomainDataMapper()
    }
}