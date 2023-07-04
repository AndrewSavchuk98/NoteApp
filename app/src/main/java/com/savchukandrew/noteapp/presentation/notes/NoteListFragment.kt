package com.savchukandrew.noteapp.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.savchukandrew.noteapp.R
import com.savchukandrew.noteapp.core.appComponent
import com.savchukandrew.noteapp.databinding.FragmentNotesListBinding
import com.savchukandrew.noteapp.presentation.navigator
import com.savchukandrew.noteapp.presentation.notes.adapters.NotesAdapter
import javax.inject.Inject

class NoteListFragment : Fragment(R.layout.fragment_notes_list) {

    private var _binding: FragmentNotesListBinding? = null
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
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NotesAdapter()
        binding.recycler.adapter = adapter
        viewModel.state.observe(viewLifecycleOwner) {
            adapter.submitList(it.notes)
        }
        binding.addNoteButton.setOnClickListener {
            navigator().goToAddNote()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}