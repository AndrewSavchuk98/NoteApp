package com.savchukandrew.noteapp.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savchukandrew.noteapp.domain.usecases.GetAllNotesUseCase
import com.savchukandrew.noteapp.presentation.notes.models.NoteUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {

    private val _state: MutableLiveData<NotesUiState> = MutableLiveData()
    val state: LiveData<NotesUiState> = _state

    init {
        //TODO("Fix this problem")
        getAllNotesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.map { note ->
                    NoteUi(
                        id = note.id,
                        title = note.title,
                        text = note.text,
                        date = note.date.toString()
                    )
                }
            }
            .subscribe {
                _state.value?.notes = it
            }

    }

}

data class NotesUiState(
    var notes: List<NoteUi>
)