package com.flowz.paging3withflow.network

import com.flowz.paging3withflow.models.RickynMortyResponse
import com.flowz.paging3withflow.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceCalls {

    @GET(Constants.END_POINT)
    suspend fun getAllCharacters(@Query("page") page:Int) : Response<RickynMortyResponse>

}