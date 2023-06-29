package com.savchukandrew.noteapp.di

import androidx.lifecycle.ViewModelProvider
import com.savchukandrew.noteapp.presentation.NotesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @Binds
    fun provideViewModelFactory(factory: NotesViewModelFactory): ViewModelProvider.Factory
}