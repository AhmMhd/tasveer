package com.abdulhakeem.tasveer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abdulhakeem.tasveer.data.Album
import com.abdulhakeem.tasveer.databinding.FragmentAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment(R.layout.fragment_albums), AdapterClickListener<Album> {

    private val viewModel: AlbumsViewModel by viewModels()
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding
    private val adapter = AlbumAdapter(this)

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

    override fun onItemClick(item: Album) {
        findNavController().navigate(AlbumsFragmentDirections.showAlbumPhotos())
    }

}