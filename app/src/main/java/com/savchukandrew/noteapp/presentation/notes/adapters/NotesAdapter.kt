package com.savchukandrew.noteapp.presentation.notes.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.savchukandrew.noteapp.databinding.NoteItemBinding
import com.savchukandrew.noteapp.presentation.notes.models.NoteUi
import kotlin.random.Random

class NotesAdapter : ListAdapter<NoteUi, NotesAdapter.NotesViewHolder>(NotesDiffUtils) {

    class NotesViewHolder(private val binding: NoteItemBinding) : ViewHolder(binding.root) {

        fun bind(note: NoteUi) {
            with(binding) {
                cardNote.setCardBackgroundColor(randomColor())
                textNoteTextView.text = note.text
                titleNoteTextView.text = note.title
                dateNoteTextView.text = note.date
            }
        }

        private fun randomColor(): Int {
            val red = Random.nextInt(256)
            val green = Random.nextInt(256)
            val blue = Random.nextInt(256)
            return Color.rgb(red, green, blue)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

object NotesDiffUtils : DiffUtil.ItemCallback<NoteUi>() {
    override fun areItemsTheSame(oldItem: NoteUi, newItem: NoteUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteUi, newItem: NoteUi): Boolean {
        return oldItem == newItem
    }

}