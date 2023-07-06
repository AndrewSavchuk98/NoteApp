package com.savchukandrew.noteapp.presentation

import androidx.fragment.app.Fragment
import com.savchukandrew.noteapp.MainActivity

interface Navigator {

    fun goToAddNote(noteId: Int?)

    fun goBack()
}

fun Fragment.navigator() = requireActivity() as MainActivity