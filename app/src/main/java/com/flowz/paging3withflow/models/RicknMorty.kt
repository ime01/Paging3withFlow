package com.flowz.paging3withflow.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RicknMorty(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
):Parcelable{

    @Parcelize
    data class Location(
        val name: String,
        val url: String
    ):Parcelable



    @Parcelize
    data class Origin(
        val name: String,
        val url: String
    ):Parcelable
}