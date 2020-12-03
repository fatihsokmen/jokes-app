package com.github.fatihsokmen.jokesapp.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.fatihsokmen.jokesapp.data.JokeModel
import com.github.fatihsokmen.jokesapp.databinding.ViewJokeBinding

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.JokeViewHolder>() {

    private val data = arrayListOf<JokeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val binding = ViewJokeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int =
        data.size

    fun setData(jokes: List<JokeModel>) {
        data.clear()
        data.addAll(jokes)
        notifyDataSetChanged()
    }

    class JokeViewHolder(
        private val binding: ViewJokeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: JokeModel) {
            binding.model = joke
            binding.executePendingBindings()
        }
    }
}