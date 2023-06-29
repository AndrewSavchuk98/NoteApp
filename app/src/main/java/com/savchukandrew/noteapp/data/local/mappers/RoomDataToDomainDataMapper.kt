package com.savchukandrew.noteapp.data.local.mappers

import com.savchukandrew.noteapp.core.Mapper
import com.savchukandrew.noteapp.data.local.NoteEntity
import com.savchukandrew.noteapp.domain.models.Note

class RoomDataToDomainDataMapper : Mapper<NoteEntity, Note> {

    override fun map(data: NoteEntity): Note {
        return Note(
            id = data.id,
            title = data.title,
            text = data.text,
            date = data.date
        )
    }
}

