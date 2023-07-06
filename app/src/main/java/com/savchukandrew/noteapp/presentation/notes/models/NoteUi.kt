package com.savchukandrew.noteapp.presentation.notes.models

data class NoteUi(
    val id: Int = 0,
    val title: String = "",
    var text: String = "",
    val date: String = ""
)
