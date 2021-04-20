package com.flowz.paging3withflow.ui.detailview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.CircleCropTransformation
import com.flowz.paging3withflow.R
import com.flowz.paging3withflow.databinding.FragmentRicknMortyGridBinding
import com.flowz.paging3withflow.databinding.FragmentRickyAndMortyDetailBinding
import com.flowz.paging3withflow.models.RicknMorty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlin.math.round

@InternalCoroutinesApi
@AndroidEntryPoint
class RickyAndMortyDetailFragment : Fragment(R.layout.fragment_ricky_and_morty_detail) {

    private var ricknMorty : RicknMorty? = null
    private  var _binding: FragmentRickyAndMortyDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ricknMorty = RickyAndMortyDetailFragmentArgs.fromBundle(it).ricknmorty
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRickyAndMortyDetailBinding.bind(view)

        binding.apply {
            rnmCharacterName.setText(ricknMorty?.name)
            rnmCharacterSpecies.setText(ricknMorty?.species)
            rnmCharacterOrigin.setText(ricknMorty?.origin?.name)
            rnmGender.setText(ricknMorty?.gender)
            rnmCharacterStatus.setText(ricknMorty?.status)

            rnmortyIcon.load(ricknMorty?.image){
                error(R.drawable.ic_baseline_error_24)
                placeholder(R.drawable.ic_baseline_error_24)
                transformations(CircleCropTransformation())
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}