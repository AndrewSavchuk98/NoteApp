package com.savchukandrew.noteapp.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.savchukandrew.noteapp.R
import com.savchukandrew.noteapp.databinding.FragmentNotesListBinding
import com.savchukandrew.noteapp.presentation.notes.adapters.NotesAdapter

class NoteListFragment : Fragment(R.layout.fragment_notes_list) {

    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}