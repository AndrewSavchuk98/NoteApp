package com.savchukandrew.noteapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.appbar.MaterialToolbar
import com.savchukandrew.noteapp.presentation.HasCustomAction
import com.savchukandrew.noteapp.presentation.Navigator
import com.savchukandrew.noteapp.presentation.note.NoteFragment
import com.savchukandrew.noteapp.presentation.notes.NoteListFragment

class MainActivity : AppCompatActivity(), Navigator {
    private lateinit var toolbar: MaterialToolbar

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        navigateTo(NoteListFragment(), true)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun goToAddNote(noteId: Int?) {
        navigateTo(NoteFragment.newInstance(noteId))
    }

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }

    private fun navigateTo(fragment: Fragment, isStartFragment: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        transaction.replace(R.id.fragmentContainer, fragment)
        if (!isStartFragment)
            transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun updateUI() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }

        if (fragment is HasCustomAction) {
            createCustomToolbar(fragment)
        } else {
            toolbar.menu.clear()
        }

    }

    private fun createCustomToolbar(action: HasCustomAction) {
        toolbar.menu.clear()
        val iconDrawable = DrawableCompat.wrap(
            ContextCompat
                .getDrawable(this, action.getDeleteAction().iconRes)!!
        )
        val menuItem = toolbar.menu.add(action.getDeleteAction().textRes)
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItem.icon = iconDrawable
        menuItem.setOnMenuItemClickListener {
            action.getDeleteAction().onCustomAction.run()
            return@setOnMenuItemClickListener true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        updateUI()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}