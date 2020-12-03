package com.github.fatihsokmen.jokesapp.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.fatihsokmen.jokesapp.databinding.FragmentJokesBinding
import com.github.fatihsokmen.jokesapp.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class JokesFragment : Fragment() {

    private val viewModel: HomeViewModel by sharedViewModel()

    private lateinit var binding: FragmentJokesBinding

    private lateinit var adapter: JokesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJokesBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(context)
        binding.jokes.layoutManager = layoutManager
        binding.jokes.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        adapter = JokesAdapter()
        binding.jokes.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.jokesModels.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }
    }
}