package com.flowz.paging3withflow.models


import com.google.gson.annotations.SerializedName

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)