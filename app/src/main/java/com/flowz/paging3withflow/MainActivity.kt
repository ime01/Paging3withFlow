package com.flowz.paging3withflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.flowz.paging3withflow.adapter.RickynMortyPagingAdapter
import com.flowz.paging3withflow.databinding.ActivityMainBinding
import com.flowz.paging3withflow.databinding.RickNMortyItemBinding
import com.flowz.paging3withflow.ui.RickyAndMortyViewModel
import com.flowz.paging3withflow.utils.getConnectionType
import com.flowz.paging3withflow.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rnmAdapter: RickynMortyPagingAdapter
    private val viewModel: RickyAndMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        if (getConnectionType(this) ){
            loadReclyclerView()
            loadData()
            showSnackbar(binding.rvRicknmorty, "Data fetched from network")
//            }
//    else{
//            showSnackbar(binding.rvRicknmorty, getString(R.string.no_network_error))
//        }

    }

    private fun loadData() {

        lifecycleScope.launch {

            viewModel.ricknMortyDataFromNetwork.collect{pagingData->
                Log.e("RnM", "${pagingData.toString()}")
                rnmAdapter.submitData(pagingData)

            }
        }
    }

    private fun loadReclyclerView() {
        rnmAdapter = RickynMortyPagingAdapter()

        binding.rvRicknmorty.layoutManager =  StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvRicknmorty.adapter = rnmAdapter


//        binding.rvRicknmorty.apply {
//
//           layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//
//            adapter = rnmAdapter
//        }
    }
}