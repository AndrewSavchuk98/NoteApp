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
import com.savchukandrew.noteapp.databinding.FragmentNoteBinding
import com.savchukandrew.noteapp.presentation.CustomAction
import com.savchukandrew.noteapp.presentation.HasCustomAction
import com.savchukandrew.noteapp.presentation.navigator
import com.savchukandrew.noteapp.presentation.notes.models.NoteUi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class NoteFragment : Fragment(R.layout.fragment_note), HasCustomAction {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<NoteViewModel> {
        factory
    }
    private var currentNoteUi: NoteUi = NoteUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
        val noteId = requireArguments().getInt(EXTRA_NOTE_ID)
        viewModel.getNoteByID(noteId)
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
            currentNoteUi = it
            updateUI(it)
        }

        val title = binding.titleEditText.text
        val text = binding.contentEditText.text

        val format = SimpleDateFormat(PATTERN_DATE, Locale.getDefault())
        val formattedDate = format.format(Date())
        binding.dateTextView.text = formattedDate

        binding.addNoteButton.setOnClickListener {
            if (currentNoteUi.id != 0) {
                val copyNote = currentNoteUi.copy(
                    text = binding.contentEditText.text.toString(),
                    title = binding.titleEditText.text.toString()
                )
                viewModel.updateNote(copyNote)
            } else {
                val noteUi = NoteUi(
                    title = title.toString(),
                    text = text.toString(),
                    date = formattedDate.toString()
                )
                viewModel.addNote(noteUi)
            }
            navigator().goBack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI(noteUi: NoteUi) {
        with(binding) {
            contentEditText.setText(noteUi.text)
            titleEditText.setText(noteUi.title)
            dateTextView.text = noteUi.date
        }
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

    override fun getDeleteAction(): CustomAction {
        return CustomAction(
            R.drawable.round_delete_24,
            R.string.delete
        ) {
            viewModel.deleteNote(currentNoteUi)
            navigator().goBack()
        }
    }

    override fun getChangeNoteBackground(): CustomAction {
        TODO("Not yet implemented")
    }
}