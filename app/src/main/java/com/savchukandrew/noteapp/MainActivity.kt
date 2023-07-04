package com.savchukandrew.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.savchukandrew.noteapp.presentation.Navigator
import com.savchukandrew.noteapp.presentation.note.NoteFragment
import com.savchukandrew.noteapp.presentation.notes.NoteListFragment

class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, NoteListFragment())
            .commit()
    }

    override fun goToAddNote() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, NoteFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }
}