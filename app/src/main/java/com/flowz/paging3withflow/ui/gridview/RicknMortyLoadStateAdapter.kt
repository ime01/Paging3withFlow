package com.flowz.paging3withflow.ui.gridview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flowz.paging3withflow.databinding.RickNMortyLoadStateFooterBinding


class RicknMortyLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<RicknMortyLoadStateAdapter.LoadStateViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
       val binding = RickNMortyLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
       holder.bind(loadState)
    }


  inner class LoadStateViewHolder(private val binding: RickNMortyLoadStateFooterBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.loadStateFooterButtonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState){
            binding.apply {
               loadStateFooterProgressbar.isVisible = loadState is LoadState.Loading
               loadStateFooterButtonRetry.isVisible = loadState !is LoadState.Loading
               loadStateFooterErrorText.isVisible = loadState !is LoadState.Loading
            }
        }
    }

}