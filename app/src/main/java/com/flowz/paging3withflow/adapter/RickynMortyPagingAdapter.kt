package com.flowz.paging3withflow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flowz.paging3withflow.R
import com.flowz.paging3withflow.databinding.RickNMortyItemBinding
import com.flowz.paging3withflow.models.RicknMorty

class RickynMortyPagingAdapter(private val listener: OnitemClickListener): PagingDataAdapter<RicknMorty, RickynMortyPagingAdapter.MyViewHolder>(diffCallback) {


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {
                rnmText.text = "${currentItem?.name}"
                val imageLink = currentItem?.image

//            loading image here with COIL libarary
                rnmImage.load(imageLink){
                    error(R.drawable.ic_baseline_error_24)
                    placeholder(R.drawable.ic_baseline_error_24)
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RickNMortyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    interface OnitemClickListener {
        fun onItemClick(ricknMorty: RicknMorty)

    }

    inner class MyViewHolder(val binding: RickNMortyItemBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position!= RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item!= null){
                        listener.onItemClick(item)
                    }
                }
            }
        }
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


