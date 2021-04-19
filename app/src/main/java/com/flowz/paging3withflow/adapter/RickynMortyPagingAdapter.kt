package com.flowz.paging3withflow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flowz.paging3withflow.databinding.RickNMortyItemBinding
import com.flowz.paging3withflow.models.RicknMorty

class RickynMortyPagingAdapter: PagingDataAdapter<RicknMorty, RickynMortyPagingAdapter.MyViewHolder>(diffCallback) {

    inner class MyViewHolder(val binding: RickNMortyItemBinding):RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.binding.apply {

            rnmText.text = "${currentItem?.name}"
            val imageLink = currentItem?.image

//            loading image here with COIL libarary
            rnmImage.load(imageLink){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RickNMortyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object{
        val diffCallback = object :DiffUtil.ItemCallback<RicknMorty>(){
            override fun areItemsTheSame(oldItem: RicknMorty, newItem: RicknMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RicknMorty, newItem: RicknMorty): Boolean {
                return oldItem == newItem
            }

        }
    }

}