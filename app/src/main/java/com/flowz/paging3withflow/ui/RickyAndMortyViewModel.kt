package com.flowz.paging3withflow.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flowz.paging3withflow.network.ApiServiceCalls
import com.flowz.paging3withflow.paging.RickynMortyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class RickyAndMortyViewModel @Inject constructor(private val apiService: ApiServiceCalls) : ViewModel() {

    val ricknMortyDataFromNetwork = Pager(PagingConfig(pageSize = 1)){
        RickynMortyPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)

    companion object{
        const val TAG = "Paging Source"
    }
}

