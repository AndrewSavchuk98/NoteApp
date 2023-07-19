package com.savchukandrew.noteapp.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface HasCustomAction {

    fun getDeleteAction(): CustomAction

    fun getChangeNoteBackground(): CustomAction

}

data class CustomAction(
    @DrawableRes val iconRes: Int,
    @StringRes val textRes: Int,
    val onCustomAction: Runnable
)