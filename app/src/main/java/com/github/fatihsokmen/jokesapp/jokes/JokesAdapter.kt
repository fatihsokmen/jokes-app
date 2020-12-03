package com.github.fatihsokmen.jokesapp.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.fatihsokmen.jokesapp.data.JokeModel
import com.github.fatihsokmen.jokesapp.databinding.ViewJokeBinding

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.CharacterViewHolder>() {

    private val data = arrayListOf<JokeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ViewJokeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int =
        data.size

    fun setData(characters: List<JokeModel>) {
        data.clear()
        data.addAll(characters)
        notifyDataSetChanged()
    }

    class CharacterViewHolder(
        private val binding: ViewJokeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: JokeModel) {
            binding.model = character
            binding.executePendingBindings()
        }
    }
}