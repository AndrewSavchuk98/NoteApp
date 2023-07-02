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
import com.savchukandrew.noteapp.domain.models.Note
import com.savchukandrew.noteapp.presentation.navigator
import com.savchukandrew.noteapp.presentation.notes.NotesViewModel
import javax.inject.Inject

class NoteFragment : Fragment(R.layout.fragment_note) {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<NotesViewModel> {
        factory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
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
        val title = binding.titleEditText.text
        val text = binding.contentEditText.text
        val date = binding.dateTextView.text
        binding.addNoteButton.setOnClickListener {
            viewModel.addNote(
                Note(
                    id = 0,
                    title = title.toString(),
                    text = text.toString(),
                    date = "March 4"
                )
            )
            navigator().goBack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): NoteFragment {
            val args = Bundle()
            val fragment = NoteFragment()
            fragment.arguments = args
            return fragment
        }
    }
}