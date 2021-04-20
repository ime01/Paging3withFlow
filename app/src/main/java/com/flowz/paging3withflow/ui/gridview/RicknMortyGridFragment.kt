package com.flowz.paging3withflow.ui.gridview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.flowz.paging3withflow.R
import com.flowz.paging3withflow.adapter.RickynMortyPagingAdapter
import com.flowz.paging3withflow.databinding.ActivityMainBinding
import com.flowz.paging3withflow.databinding.FragmentRicknMortyGridBinding
import com.flowz.paging3withflow.models.RicknMorty
import com.flowz.paging3withflow.ui.RickyAndMortyViewModel
import com.flowz.paging3withflow.utils.getConnectionType
import com.flowz.paging3withflow.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@AndroidEntryPoint
class RicknMortyGridFragment : Fragment(R.layout.fragment_rickn_morty_grid), RickynMortyPagingAdapter.OnitemClickListener{

    private  var _binding: FragmentRicknMortyGridBinding? = null
    private val binding get() = _binding!!
    private lateinit var rnmAdapter: RickynMortyPagingAdapter
    private val viewModel: RickyAndMortyViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRicknMortyGridBinding.bind(view)

        if (getConnectionType(requireContext()) ){
            loadReclyclerView()
            loadData()
            rnmAdapter.addLoadStateListener {loadState->
                binding.apply {
                    progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    rvRicknmorty.isVisible = loadState.source.refresh is LoadState.NotLoading
                    buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                    errorText.isVisible = loadState.source.refresh is LoadState.Error

                    if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached&& rnmAdapter.itemCount<1){
                        rvRicknmorty.isVisible = false
                        errorText.isVisible = true
                    }else{
                        errorText.isVisible = false
                    }
                }
            }

            showSnackbar(binding.rvRicknmorty, getString(R.string.data_from_network))
        } else{
            showSnackbar(binding.rvRicknmorty, getString(R.string.no_network_error))
        }

        binding.buttonRetry.setOnClickListener {
            rnmAdapter.retry()
        }


    }

    @InternalCoroutinesApi
    private fun loadData() {

        lifecycleScope.launch {

            viewModel.ricknMortyDataFromNetwork.collect{
                Log.e("RnM", "$it")
                rnmAdapter.submitData(it)

            }
        }
        binding.progressBar.visibility = View.GONE
    }

    private fun loadReclyclerView() {
        rnmAdapter = RickynMortyPagingAdapter(this@RicknMortyGridFragment)

        binding.rvRicknmorty.apply {

            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            adapter = rnmAdapter.withLoadStateHeaderAndFooter(
                header = RicknMortyLoadStateAdapter{rnmAdapter.retry()},
                footer = RicknMortyLoadStateAdapter{rnmAdapter.retry()}
            )
            setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onItemClick(ricknMorty: RicknMorty) {
        val sendRicknMorty = RicknMortyGridFragmentDirections.actionRicknMortyGridFragmentToRickyAndMortyDetailFragment()
        sendRicknMorty.ricknmorty = ricknMorty
        Navigation.findNavController(requireView()).navigate(sendRicknMorty)

    }
}