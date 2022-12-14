package com.abdulhakeem.tasveer.ui.media

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.abdulhakeem.tasveer.R
import com.abdulhakeem.tasveer.databinding.FragmentAlbumPhotosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment(R.layout.fragment_album_photos) {
    private val adapter = PhotosAdapter()
    private val args: PhotosFragmentArgs by navArgs()
    private val viewModel: PhotosViewModel by viewModels()
    private var _binding: FragmentAlbumPhotosBinding? = null
    private val binding get() = _binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAlbumPhotosBinding.bind(view)
        viewModel.handleArgs(args)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.list?.adapter = adapter

        startObservers()
    }

    private fun startObservers() {
        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}