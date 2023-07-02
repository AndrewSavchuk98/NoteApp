package com.savchukandrew.noteapp.core

import androidx.fragment.app.Fragment
import com.savchukandrew.noteapp.NoteApp

fun Fragment.appComponent() = (requireActivity().application as NoteApp).appComponent