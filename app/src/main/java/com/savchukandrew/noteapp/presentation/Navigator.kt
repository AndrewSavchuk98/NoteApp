package com.savchukandrew.noteapp.presentation

import androidx.fragment.app.Fragment
import com.savchukandrew.noteapp.MainActivity

interface Navigator {

    fun goToAddNote()

    fun goBack()
}

fun Fragment.navigator() = requireActivity() as MainActivity