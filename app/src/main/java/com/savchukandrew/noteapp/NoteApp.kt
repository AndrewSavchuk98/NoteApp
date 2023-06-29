package com.savchukandrew.noteapp

import android.app.Application
import com.savchukandrew.noteapp.di.AppComponent
import com.savchukandrew.noteapp.di.DaggerAppComponent

class NoteApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()
    }
}