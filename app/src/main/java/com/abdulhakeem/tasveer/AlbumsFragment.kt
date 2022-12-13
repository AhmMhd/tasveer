package com.abdulhakeem.tasveer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.abdulhakeem.tasveer.databinding.FragmentAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment(R.layout.fragment_albums) {

    private val viewModel: AlbumsViewModel by viewModels()
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding
    private val adapter = AlbumAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAlbumsBinding.bind(view)
        binding?.viewModel = viewModel
        binding?.adapter = adapter
        binding?.lifecycleOwner = viewLifecycleOwner

        startObservers()
    }

    private fun startObservers() {
        viewModel.albums.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}