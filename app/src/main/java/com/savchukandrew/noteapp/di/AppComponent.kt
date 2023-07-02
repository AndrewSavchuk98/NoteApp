package com.savchukandrew.noteapp.di

import android.content.Context
import com.savchukandrew.noteapp.presentation.note.NoteFragment
import com.savchukandrew.noteapp.presentation.notes.NoteListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [AppModule::class,
        DataModule::class,
        RoomModule::class,
        MapperModule::class,
        UseCasesModule::class]
)
interface AppComponent {

    fun inject(fragment: NoteListFragment)
    fun inject(fragment: NoteFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}