package com.savchukandrew.noteapp.presentation.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.savchukandrew.noteapp.R
import com.savchukandrew.noteapp.core.appComponent
import com.savchukandrew.noteapp.core.log
import com.savchukandrew.noteapp.databinding.FragmentNoteBinding
import com.savchukandrew.noteapp.domain.models.Note
import com.savchukandrew.noteapp.presentation.navigator
import com.savchukandrew.noteapp.presentation.notes.NotesViewModel
import com.savchukandrew.noteapp.presentation.notes.models.NoteUi
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class NoteFragment : Fragment(R.layout.fragment_note) {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<NoteViewModel> {
        factory
    }

    private var noteId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
        noteId = requireArguments().getInt(EXTRA_NOTE_ID)
        viewModel.getNoteByID(noteId)
        log("OnCreate noteId: $noteId")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.note.observe(viewLifecycleOwner) {
            Timber.d("NoteUi when observe $it")
            with(binding) {
                updateUI(it)
            }
        }

        val title = binding.titleEditText.text
        val text = binding.contentEditText.text

        val format = SimpleDateFormat(PATTERN_DATE, Locale.getDefault())
        val formattedDate = format.format(Date())
        binding.dateTextView.text = formattedDate

        binding.addNoteButton.setOnClickListener {
            log("OnClick noteId: $noteId")
            val noteUi = NoteUi(
                id = noteId,
                title = title.toString(),
                text = text.toString(),
                date = formattedDate.toString()
            )

            log("NoteUi in OnClick $noteUi")
            if (noteId != 0) {
                log("Update called")
                viewModel.updateNote(noteUi)
            } else {
                log("Add called")
                viewModel.addNote(noteUi)
            }
            navigator().goBack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentNoteBinding.updateUI(noteUi: NoteUi) {
        //  noteId = noteUi.id
        binding.titleEditText.setText(noteUi.title)
        binding.contentEditText.setText(noteUi.text)
        binding.dateTextView.text = noteUi.date
    }

    companion object {
        private const val EXTRA_NOTE_ID = "Note_id"
        private const val PATTERN_DATE = "d MMMM HH:mm"

        fun newInstance(noteId: Int?): NoteFragment {
            val args = Bundle()
            if (noteId != null)
                args.putInt(EXTRA_NOTE_ID, noteId)
            val fragment = NoteFragment()
            fragment.arguments = args
            return fragment
        }
    }
}