package com.abdulhakeem.tasveer.ui.albums

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abdulhakeem.tasveer.data.model.Album
import com.abdulhakeem.tasveer.databinding.FragmentAlbumsBinding
import com.abdulhakeem.tasveer.ui.common.AdapterClickListener
import com.abdulhakeem.tasveer.ui.common.hasStoragePermission
import com.abdulhakeem.tasveer.ui.common.requestStoragePermission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment(), AdapterClickListener<Album> {

    private val viewModel: AlbumsViewModel by viewModels()
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding
    private val adapter = AlbumAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumsBinding.inflate(LayoutInflater.from(context), container, false)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.list?.adapter = adapter

        startObservers()

        requestStoragePermissionIfNotGranted(context)

        return binding?.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchAlbums()
    }

    private fun requestStoragePermissionIfNotGranted(context: Context?) {
        context?.run {
            if (hasStoragePermission().not()) {
                requestStoragePermission(callback = { isGranted ->
                    if (isGranted) {
                        viewModel.fetchAlbums()
                    }
                })
            }
        }
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
        findNavController().navigate(AlbumsFragmentDirections.showAlbumPhotos(item))
    }

}