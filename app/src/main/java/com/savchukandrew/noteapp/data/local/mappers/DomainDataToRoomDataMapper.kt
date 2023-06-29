package com.savchukandrew.noteapp.data.local.mappers

import com.savchukandrew.noteapp.core.Mapper
import com.savchukandrew.noteapp.data.local.NoteEntity
import com.savchukandrew.noteapp.domain.models.Note

class DomainDataToRoomDataMapper: Mapper<Note, NoteEntity> {
    override fun map(data: Note): NoteEntity {
        return NoteEntity(
            id = data.id,
            title = data.title,
            text = data.text,
            date = data.date
        )
    }
}