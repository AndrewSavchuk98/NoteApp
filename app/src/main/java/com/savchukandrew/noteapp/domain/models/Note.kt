package com.savchukandrew.noteapp.domain.models

import java.util.Date

data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val date: Date
)
