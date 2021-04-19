package com.flowz.paging3withflow.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flowz.paging3withflow.models.RicknMorty
import com.flowz.paging3withflow.network.ApiServiceCalls

class RickynMortyPagingSource(private val apiService: ApiServiceCalls): PagingSource<Int, RicknMorty>() {
    override fun getRefreshKey(state: PagingState<Int, RicknMorty>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RicknMorty> {
        return try {
            val currentPage = params.key ?:1

            val response = apiService.getAllCharacters(currentPage)
//            Log.e(TAG, "$response")

            val data = response.body()?.results?: emptyList()

            val responseData = mutableListOf<RicknMorty>()
            responseData.addAll(data)

            Log.e(TAG, "$responseData")

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage==1) null else -1,
                nextKey = currentPage.plus(1)
            )

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    companion object{
        const val TAG = "Paging Source"
    }

}